package com.itude.mobile.mobbl.samples.masterdetail.datahandlers;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpUriRequest;

import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.model.MBDocumentFactory;
import com.itude.mobile.mobbl.core.services.datamanager.handlers.MBRESTServiceDataHandler;

public class PlantDataHandler extends MBRESTServiceDataHandler
{

  public PlantDataHandler()
  {
    setDocumentFactoryType(MBDocumentFactory.PARSER_XML);
  }

  @Override
  protected void setupHttpUriRequestHeader(HttpUriRequest httpUriRequest)
  {
    httpUriRequest.setHeader("Content-Type", "text/xml");
    httpUriRequest.setHeader("Accept", "text/xml,application/xml");
  }

  @Override
  protected String getOperation(MBDocument doc)
  {
    return "GET";
  }

  @Override
  protected String getRequestUrlFromDocument(String urlString, MBDocument document) throws UnsupportedEncodingException
  {
    return urlString;
  }
}
