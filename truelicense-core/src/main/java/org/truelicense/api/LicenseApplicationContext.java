/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package org.truelicense.api;

import org.truelicense.api.auth.Authentication;
import org.truelicense.api.crypto.Encryption;
import org.truelicense.api.io.Sink;
import org.truelicense.api.io.Source;
import org.truelicense.api.io.Store;
import org.truelicense.api.misc.ContextProvider;
import org.truelicense.api.misc.Injection;
import org.truelicense.obfuscate.Obfuscate;
import org.truelicense.obfuscate.ObfuscatedString;

import javax.annotation.CheckForNull;
import java.nio.file.Path;

/**
 * A context which has been derived from a
 * {@linkplain LicenseManagementContext license management context}.
 * <p>
 * Applications have no need to implement this interface and should not do so
 * because it may be subject to expansion in future versions.
 *
 * @author Christian Schlichtherle
 */
public interface LicenseApplicationContext
extends ContextProvider<LicenseManagementContext> {

    /**
     * Returns a source which loads the resource with the given {@code name}.
     * The provided string should be computed on demand from an obfuscated form,
     * e.g. by annotating a constant string value with the &#64;{@link Obfuscate}
     * annotation and processing it with the TrueLicense Maven Plugin.
     * <p>
     * The resource will get loaded using the class loader as defined by the
     * root license management context.
     *
     * @param  name the name of the resource to load.
     * @return A source which loads the resource with the given {@code name}.
     */
    Source resource(String name);

    /**
     * Returns a source which reads from standard input without ever closing it.
     */
    Source input();

    /**
     * Returns a sink which writes to standard output without ever closing it.
     */
    Sink output();

    /** Returns a store for the given path. */
    Store pathStore(Path path);

    /** Returns a new memory store. */
    Store memoryStore();

    /**
     * Returns a store for the system preferences node for the package of the
     * given class.
     * Note that the class should be exempt from byte code obfuscation or
     * otherwise you might use an unintended store location and risk a
     * collision with third party software.
     */
    Store systemNodeStore(Class<?> classInPackage);

    /**
     * Returns a store for the user preferences node for the package of the
     * given class.
     * Note that the class should be exempt from byte code obfuscation or
     * otherwise you might use an unintended store location and risk a
     * collision with third party software.
     */
    Store userNodeStore(Class<?> classInPackage);

    /**
     * Injects a Key Store Based {@link Authentication} (KSBA)
     * into some target.
     */
    interface KsbaInjection<Target> extends Injection<Target> {

        /**
         * Sets the source for the key store.
         *
         * @return {@code this}
         */
        KsbaInjection<Target> loadFrom(Source source);

        /**
         * Sets the resource name of the key store.
         *
         * @return {@code this}
         */
        KsbaInjection<Target> loadFromResource(String name);

        /**
         * Sets the type of the key store,
         * for example {@code "JCEKS"} or {@code "JKS"}.
         *
         * @return {@code this}
         */
        KsbaInjection<Target> storeType(@CheckForNull String storeType);

        /**
         * Sets the password for verifying the integrity of the key store.
         *
         * @return {@code this}
         */
        KsbaInjection<Target> storePassword(ObfuscatedString storePassword);

        /**
         * Sets the alias name of the key entry.
         *
         * @return {@code this}
         */
        KsbaInjection<Target> alias(String alias);

        /**
         * Sets the password for accessing the private key in the key entry.
         * A private key entry is only required to create license keys, that is
         * for any {@linkplain LicenseVendorManager license vendor manager}
         * and for any
         * {@linkplain LicenseConsumerManager license consumer manager}
         * for a free trial period.
         * If this method is not called or if an empty password is passed, then
         * the {@linkplain #storePassword(ObfuscatedString) key store password}
         * is used instead.
         *
         * @return {@code this}
         */
        KsbaInjection<Target> keyPassword(ObfuscatedString keyPassword);
    }

    /**
     * Injects a Password Based {@link Encryption} (PBE)
     * into some target.
     */
    interface PbeInjection<Target> extends Injection<Target> {

        /**
         * Sets the algorithm name.
         *
         * @return {@code this}
         */
        PbeInjection<Target> algorithm(@CheckForNull String algorithm);

        /**
         * Sets the password used to generate a secret key for
         * encryption/decryption.
         *
         * @return {@code this}
         */
        PbeInjection<Target> password(ObfuscatedString password);
    }
}
