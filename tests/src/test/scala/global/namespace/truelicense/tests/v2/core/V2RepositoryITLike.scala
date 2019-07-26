/*
 * Copyright (C) 2005 - 2019 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package global.namespace.truelicense.tests.v2.core

import global.namespace.truelicense.api.auth.RepositoryContext
import global.namespace.truelicense.tests.core.{RepositoryITLike, TestContext}
import global.namespace.truelicense.v2.core.auth.{V2RepositoryContext, V2RepositoryModel}

trait V2RepositoryITLike extends RepositoryITLike[V2RepositoryModel] {
  this: TestContext =>

  val context: RepositoryContext[V2RepositoryModel] = new V2RepositoryContext
}