/**
 * ownCloud Android client application
 *
 * @author Juan Carlos Garrote Gascón
 *
 * Copyright (C) 2022 ownCloud GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.owncloud.android.data.transfers.datasources

import androidx.lifecycle.LiveData
import com.owncloud.android.domain.transfers.model.OCTransfer
import com.owncloud.android.domain.transfers.model.TransferResult
import com.owncloud.android.domain.transfers.model.TransferStatus

interface LocalTransferDataSource {
    fun saveTransfer(transfer: OCTransfer): Long
    fun updateTransfer(transfer: OCTransfer)
    fun updateTransferStatusToInProgressById(id: Long)
    fun updateTransferStatusToEnqueuedById(id: Long)
    fun updateTransferWhenFinished(
        id: Long,
        status: TransferStatus,
        transferEndTimestamp: Long,
        lastResult: TransferResult
    )

    fun updateTransferLocalPath(id: Long, localPath: String)
    fun updateTransferStorageDirectoryInLocalPath(
        id: Long,
        oldDirectory: String,
        newDirectory: String
    )

    fun deleteTransferById(id: Long)
    fun deleteAllTransfersFromAccount(accountName: String)
    fun getTransferById(id: Long): OCTransfer?
    fun getAllTransfers(): List<OCTransfer>
    fun getAllTransfersAsLiveData(): LiveData<List<OCTransfer>>
    fun getLastTransferFor(remotePath: String, accountName: String): OCTransfer?
    fun getCurrentAndPendingTransfers(): List<OCTransfer>
    fun getFailedTransfers(): List<OCTransfer>
    fun getFinishedTransfers(): List<OCTransfer>
    fun clearFailedTransfers()
    fun clearSuccessfulTransfers()
}
