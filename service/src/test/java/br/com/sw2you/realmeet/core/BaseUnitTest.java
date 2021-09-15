package br.com.sw2you.realmeet.core;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Classe Base para definicao de tudo que é comum para as classes de test
 * - @ExtendWith(MockitoExtension.class): Adiciona suporte do Junit ao Mockito
 */
@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {}