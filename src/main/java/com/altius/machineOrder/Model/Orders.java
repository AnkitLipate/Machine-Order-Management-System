/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder.Model;

import java.io.Serializable;
import java.sql.Date;
import jxl.write.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author altius
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Orders implements Serializable {

    @EqualsAndHashCode.Include
    private int orderId;
    private IdAndLabel user;
    private String code;
    private IdAndLabel orderStatus;
    private Date orderDate;

}
