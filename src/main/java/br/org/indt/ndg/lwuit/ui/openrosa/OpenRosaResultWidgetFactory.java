package br.org.indt.ndg.lwuit.ui.openrosa;

import br.org.indt.ndg.lwuit.ui.UIUtils;
import br.org.indt.ndg.lwuit.ui.style.NDGStyleToolbox;
import com.nokia.xfolite.xforms.dom.BoundElement;
import com.nokia.xfolite.xforms.model.datatypes.DataTypeBase;
import com.nokia.xfolite.xml.dom.Element;
import com.nokia.xfolite.xml.dom.WidgetFactory;
import com.nokia.xfolite.xml.xpath.XPathNSResolver;
import com.sun.lwuit.Container;
import com.sun.lwuit.Font;
import com.sun.lwuit.layouts.BoxLayout;
import java.util.Hashtable;

/**
 *
 * @author damian.janicki
 */
public class OpenRosaResultWidgetFactory implements WidgetFactory, XPathNSResolver{

    private static OpenRosaResourceManager resourceManager = new OpenRosaResourceManager();
    protected static Hashtable resources = new Hashtable();

    private Container rootContainer = null;
    private Font questionFont = NDGStyleToolbox.fontMedium;
    private Font answerFont = NDGStyleToolbox.fontMedium;

    public OpenRosaResultWidgetFactory(Container cont){
        rootContainer = cont;
        rootContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        resources.clear();
    }

    public void elementParsed(Element el) {
    }

    public void childrenParsed(Element el) {
        String tagName = el.getLocalName();
        BoundElement binding = null;
        if (el instanceof BoundElement) {
            binding = (BoundElement) el;
        }

        if( tagName.equals("input")  || tagName.equals("secret")){
            addInputPreview(binding);
        } else if (tagName.equals("select") || tagName.equals("select1")) {
            addSelectPreview(binding);
        } else if (tagName.equals("value")) {
            addTextValue(el);
        }
    }

    public void addSelectPreview(BoundElement element){
        String questionLabel = resourceManager.tryGetLabelForElement(element);
        String questionValue = element.getStringValue();
        questionValue = questionValue.replace(' ', '\n');

        addQuestionComponent(questionLabel, questionValue);
    }

    public void addInputPreview(BoundElement element){

        String questionLabel = resourceManager.tryGetLabelForElement(element);
        String questionValue = "";

        DataTypeBase a = element.getDataType();
        if (a != null && a.getBaseTypeID() == DataTypeBase.XML_SCHEMAS_UNKNOWN) {
            questionValue = "Unsuportted type";//TODO Localize
        } else{
            questionValue = element.getStringValue();
        }

        addQuestionComponent(questionLabel, questionValue);
    }

    private void addQuestionComponent(String label, String value){
        Container container = new Container( new BoxLayout( BoxLayout.Y_AXIS ) );
        container.addComponent( UIUtils.createTextArea( label + ":", questionFont, NDGStyleToolbox.getInstance().questionPreviewColor) );
        container.addComponent( UIUtils.createTextArea( value, answerFont, NDGStyleToolbox.getInstance().answerPreviewColor ) );

        rootContainer.addComponent(container);
    }

    public void addTextValue(Element el){
        Element parent = (Element)el.getParentNode();
        if(!parent.getNodeName().equals("text")){
            return;
        }

        String id = parent.getAttribute("id");
        String value = el.getText();

        resourceManager.put(id, value);
    }

    public void removingElement(Element el) {
    }

    public void elementInitialized(Element el) {
    }

    public void childrenInitialized(Element el) {
    }

    public String lookupNamespaceURI(String prefix) {
        return "";
    }
}
