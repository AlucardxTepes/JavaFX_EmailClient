package com.alucard.controller;

import com.alucard.model.ModelAccess;

/**
 * Created by Alucard on 07-Jun-17.
 */
public abstract class AbstractController {

  private ModelAccess modelAccess;

  public AbstractController(ModelAccess modelAccess) {
    this.modelAccess = modelAccess;
  }

  public ModelAccess getModelAccess() {
    return modelAccess;
  }
}
