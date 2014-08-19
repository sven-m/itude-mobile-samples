package com.itude.mobile.mobbl.customviewcontrollersample.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itude.mobile.mobbl.core.model.MBDocument;
import com.itude.mobile.mobbl.core.model.MBElement;

public class DocumentAdapter extends BaseAdapter
{
  private final Context              context;
  private final String               dataPath;
  private final MBDocument           document;
  private final int                  rowLayoutResource;
  private final Map<String, Integer> itemPathsLayoutMap;

  public DocumentAdapter(final Context context, final MBDocument document, final String dataPath, final int rowLayoutResource,
                         final Map<String, Integer> itemPathsLayoutMap)
  {
    super();

    this.context = context;
    this.dataPath = dataPath;
    this.document = document;
    this.rowLayoutResource = rowLayoutResource;
    this.itemPathsLayoutMap = itemPathsLayoutMap;
  }

  @Override
  public int getCount()
  {
    return getElements().size();
  }

  @Override
  public Object getItem(final int position)
  {
    return getElements().get(position);
  }

  @Override
  public long getItemId(final int position)
  {
    return getItem(position).hashCode();
  }

  public String getItemPath(final int position)
  {
    return String.format("%s[%d]", getDataPath(), position);
  }

  @Override
  public View getView(final int position, final View convertView, final ViewGroup parent)
  {
    View resultingView;
    if (convertView == null)
    {
      resultingView = loadView(parent);
    }
    else
    {
      resultingView = convertView;
    }

    final ViewHolder viewHolder = (ViewHolder) resultingView.getTag();

    populateViewsInHolder(position, viewHolder);

    return resultingView;
  }

  private void populateViewsInHolder(final int position, final ViewHolder viewHolder)
  {
    final MBElement element = getElements().get(position);

    for (final String path : getItemPathsLayoutMap().keySet())
    {
      viewHolder.getTextView(path).setText(element.<String> getValueForPath(path));
    }
  }

  private View loadView(final ViewGroup parent)
  {
    final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    final View view = inflater.inflate(getRowLayoutResource(), parent, false);

    final ViewHolder viewHolder = new ViewHolder();

    for (final Entry<String, Integer> pathLayoutEntry : getItemPathsLayoutMap().entrySet())
    {
      final String key = pathLayoutEntry.getKey();
      final TextView textView = (TextView) view.findViewById(pathLayoutEntry.getValue());

      viewHolder.setTextView(key, textView);
    }

    view.setTag(viewHolder);

    return view;
  }

  public String getDataPath()
  {
    return this.dataPath;
  }

  public MBDocument getDocument()
  {
    return this.document;
  }

  private List<MBElement> getElements()
  {
    return getDocument().getValueForPath(getDataPath());
  }

  public Map<String, Integer> getItemPathsLayoutMap()
  {
    return this.itemPathsLayoutMap;
  }

  public Context getContext()
  {
    return this.context;
  }

  public int getRowLayoutResource()
  {
    return this.rowLayoutResource;
  }

  private static class ViewHolder
  {
    private final Map<String, TextView> textViewMap;

    public ViewHolder(final Map<String, TextView> textViewMap)
    {
      if (textViewMap == null)
      {
        this.textViewMap = new HashMap<String, TextView>();
      }
      else
      {
        this.textViewMap = textViewMap;
      }
    }

    public ViewHolder()
    {
      this(null);
    }

    public TextView getTextView(final String key)
    {
      return getViewMap().get(key);
    }

    public void setTextView(final String key, final TextView textView)
    {
      getViewMap().put(key, textView);
    }

    public Map<String, TextView> getViewMap()
    {
      return this.textViewMap;
    }
  }

}
