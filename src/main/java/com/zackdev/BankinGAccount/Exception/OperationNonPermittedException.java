package com.zackdev.BankinGAccount.Exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNonPermittedException extends RuntimeException{
    private final String errorMessage;
    private final String errorCode;
    private final String operationId;
    private final String source;
    private final String dependency;


}
