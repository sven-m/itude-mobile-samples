package com.itude.mobile.template.datahandlers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import com.itude.mobile.mobbl.core.configuration.webservices.MBEndPointDefinition;
import com.itude.mobile.mobbl.core.controller.MBApplicationFactory;
import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.model.MBDocumentFactory;
import com.itude.mobile.mobbl.core.services.MBLocalizationService;
import com.itude.mobile.mobbl.core.services.MBMetadataService;
import com.itude.mobile.mobbl.core.services.MBResultListener;
import com.itude.mobile.mobbl.core.services.MBResultListenerDefinition;
import com.itude.mobile.mobbl.core.services.datamanager.handlers.MBRESTServiceDataHandler;
import com.itude.mobile.mobbl.core.services.datamanager.handlers.exceptions.MBServerErrorException;
import com.itude.mobile.web.exceptions.ItudeRuntimeException;

// TODO: this is horrible :') Web's MBRESTServiceDataHandler should be updated to make this copy-pasting unneeded
public class PlantDataHandler extends MBRESTServiceDataHandler
{
  private static final long          serialVersionUID    = 1L;
  private static final Logger        LOGGER              = Logger.getLogger(PlantDataHandler.class);
  private final MBApplicationFactory _applicationFactory = MBApplicationFactory.getInstance();

  public PlantDataHandler()
  {
    setDocumentFactoryType(MBDocumentFactory.PARSER_XML);
  }

  @Override
  public MBDocument doLoadDocument(String documentName, MBDocument args)
  {
    MBEndPointDefinition endPoint = getEndPointForDocument(documentName);

    if (endPoint != null)
    {
      LOGGER.debug("MBRESTServiceDataHandler:loadDocument " + documentName + " from " + endPoint.getEndPointUri());

      String dataString = null;
      MBDocument responseDoc = null;
      try
      {
        HttpGet httpGet = new HttpGet(endPoint.getEndPointUri());
        // Content-Type must be set because otherwise the MidletCommandProcessor servlet cannot read the XML
        httpGet.setHeader("Content-Type", "text/xml");
        httpGet.setHeader("Accept", "text/xml,application/xml");

        HttpParams httpParameters = new BasicHttpParams();
        // Set the timeout in milliseconds until a connection is established.
        int timeoutConnection = DEFAULT_TIMEOUTCONNECTION;
        // Set the default socket timeout (SO_TIMEOUT) 
        // in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = DEFAULT_TIMEOUT_SOCKET;

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(registry, timeoutConnection, TimeUnit.MILLISECONDS);
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        cm.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE);

        HttpClient httpClient = new DefaultHttpClient(cm, httpParameters);
        if (endPoint.getTimeout() > 0)
        {
          timeoutSocket = endPoint.getTimeout() * 1000;
          timeoutConnection = endPoint.getTimeout() * 1000;
        }
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        int responseCode = httpResponse.getStatusLine().getStatusCode();
        String responseMessage = httpResponse.getStatusLine().getReasonPhrase();
        if (responseCode != HttpStatus.SC_OK)
        {
          LOGGER.error("MBRESTServiceDataHandler.loadDocument: Received HTTP responseCode=" + responseCode + ": " + responseMessage);
        }

        HttpEntity entity = httpResponse.getEntity();
        if (entity != null)
        {
          InputStream inStream = entity.getContent();
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          byte[] buffer = new byte[1024];
          int i = inStream.read(buffer);
          while (i > -1)
          {
            bos.write(buffer, 0, i);
            i = inStream.read(buffer);
          }
          inStream.close();
          dataString = new String(bos.toByteArray());
        }

        boolean serverErrorHandled = false;

        for (MBResultListenerDefinition lsnr : endPoint.getResultListeners())
        {
          if (lsnr.matches(dataString))
          {
            MBResultListener rl = _applicationFactory.createResultListener(lsnr.getName());
            rl.handleResult(dataString, args, lsnr);
            serverErrorHandled = true;
          }
        }

        /*       if (delegate.err != nil) {
                   String errorMessage = null;
                   //[[UIApplication sharedApplication] setNetworkActivityIndicatorVisible:NO];
                   Log.w("MOBBL","An error ("+errorMessage+") occured while accessing endpoint "+endPoint.getEndPointUri());
                   throw new NetworkErrorException(MBLocalizationService.getInstance().textForKey((errorMessage);
               }
               //[[UIApplication sharedApplication] setNetworkActivityIndicatorVisible:NO];
        */
        if (dataString != null)
        {
          byte[] data = dataString.getBytes();
          responseDoc = MBDocumentFactory.getInstance().getDocumentWithData(data,
                                                                            getDocumentFactoryType(),
                                                                            MBMetadataService.getInstance()
                                                                                .getDefinitionForDocumentName(documentName));
        }
        // if the response document is empty and unhandled by endpoint listeners let the user know there is a problem
        if (!serverErrorHandled && responseDoc == null)
        {
          String msg = MBLocalizationService.getInstance().getTextForKey("The server returned an error. Please try again later");
          //                if(delegate.err != nil) {
          //                    msg = [NSString stringWithFormat:@"%@ %@: %@", msg, delegate.err.domain, delegate.err.code];
          //                }
          throw new MBServerErrorException(msg);
        }
      }
      // TODO: clean up exception handling
      catch (Exception e)
      {
        // debug in stead of info because it can contain a password
        LOGGER.info("Received:\n" + dataString);
        if (e instanceof RuntimeException) throw (RuntimeException) e;
        else throw new ItudeRuntimeException(e);
      }
      return responseDoc;
    }
    else
    {
      LOGGER.warn("No endpoint defined for document name " + documentName);
      return null;
    }
  }

}
