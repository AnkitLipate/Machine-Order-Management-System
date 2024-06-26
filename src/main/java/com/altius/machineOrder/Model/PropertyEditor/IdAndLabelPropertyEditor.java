/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Model.PropertyEditor;

import com.altius.machineOrder.Model.IdAndLabel;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author altius
 */
public class IdAndLabelPropertyEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new IdAndLabel(text,null));
    }

    @Override
    public String getAsText() {
        if (getValue()==null){
        return null;
    }
        else {
            return ((IdAndLabel) getValue()).getId();
        }
        
        
    }
    
    
    
}
