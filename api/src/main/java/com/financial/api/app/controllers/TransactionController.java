package com.financial.api.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users/{userId}/accounts/{accountId}/transactions")
public class TransactionController {
}
