/**
 * 
 */
package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hoang.nguyen_viet
 *
 */
@Data
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;

    private String errorMessage;
}
