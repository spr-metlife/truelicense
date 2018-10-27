/*
 * Copyright (C) 2005-2017 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package net.truelicense.api.crypto;

import net.truelicense.api.LicenseManagementContextBuilder;
import net.truelicense.api.builder.GenBuilder;
import net.truelicense.api.builder.GenChildBuilder;
import net.truelicense.api.passwd.PasswordProtection;

/**
 * A child builder for an encryption which injects a password based encryption into some parent builder.
 *
 * @param <ParentBuilder> the type of the parent builder.
 * @author Christian Schlichtherle
 */
public interface EncryptionChildBuilder<ParentBuilder extends GenBuilder<?>> extends GenChildBuilder<ParentBuilder> {

    /**
     * Sets the name of the password based encryption algorithm (optional).
     * If this method is not called, then the name is inherited from the license management context.
     *
     * @return {@code this}
     * @see LicenseManagementContextBuilder#encryptionAlgorithm(String)
     */
    EncryptionChildBuilder<ParentBuilder> algorithm(String algorithm);

    /**
     * Sets the password protection which is used for generating a secret key for encryption/decryption.
     *
     * @return {@code this}
     */
    EncryptionChildBuilder<ParentBuilder> protection(PasswordProtection protection);
}