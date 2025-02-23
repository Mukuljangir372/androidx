/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.core.telecom

import android.os.ParcelUuid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * DSL interface to provide and receive updates about a single call session. The scope should be
 * used to provide updates to the call state and receive updates about a call state.  Example usage:
 *
 *    // initiate a call and control via the CallControlScope
 *    mCallsManager.addCall(
 *        callAttributes,
 *        onAnswerLambda,
 *        onDisconnectLambda,
 *        onSetActiveLambda,
 *        onSetInActiveLambda
 *        ) { // This block represents the CallControlScope
 *
 *           // UI flow sends an update to a call state, relay the update to Telecom
 *           disconnectCallButton.setOnClickListener {
 *             val wasSuccessful = disconnect(reason) // waits for telecom async. response
 *             // update UI
 *           }
 *
 *           // Collect updates
 *           currentCallEndpoint.collect { // access the new [CallEndpoint] here }
 *     }
 */
interface CallControlScope : CoroutineScope {
    /**
     * @return the 128-bit universally unique identifier Telecom assigned to this CallControlScope.
     * This id can be helpful for debugging when dumping the telecom system.
     */
    fun getCallId(): ParcelUuid

    /**
     * Inform Telecom that your app wants to make this call active. This method should be called
     * when either an outgoing call is ready to go active or a held call is ready to go active
     * again. For incoming calls that are ready to be answered, use [answer].
     *
     * @return Telecom will return [CallControlResult.Success] if your app is able to set the call
     * active. Otherwise [CallControlResult.Error] will be returned (ex. another call is active and
     * telecom cannot set this call active until the other call is held or disconnected) with an
     * error code indicating why setActive failed.
     */
    suspend fun setActive(): CallControlResult

    /**
     * Inform Telecom that your app wants to make this call inactive. This the same as hold for two
     * call endpoints but can be extended to setting a meeting to inactive.
     *
     * @return Telecom will return [CallControlResult.Success] if your app is able to set the call
     * inactive. Otherwise,  [CallControlResult.Error] will be returned with an error code
     * indicating why setInActive failed.
     */
    suspend fun setInactive(): CallControlResult

    /**
     * Inform Telecom that your app wants to make this incoming call active.  For outgoing calls
     * and calls that have been placed on hold, use [setActive].
     *
     * @param [callType] that call is to be answered as.
     *
     * @return Telecom will return [CallControlResult.Success] if your app is able to answer the
     * call. Otherwise [CallControlResult.Error] will be returned with an  error code indicating
     * why answer failed (ex. another call is active and telecom cannot set this call active until
     * the other call is held or disconnected). This means that your app cannot answer this call at
     * this time.
     */
    suspend fun answer(@CallAttributesCompat.Companion.CallType callType: Int): CallControlResult

    /**
     * Inform Telecom that your app wishes to disconnect the call and remove the call from telecom
     * tracking.
     *
     * @param disconnectCause represents the cause for disconnecting the call.  The only valid
     *                        codes for the [android.telecom.DisconnectCause] passed in are:
     *                        <ul>
     *                        <li>[DisconnectCause#LOCAL]</li>
     *                        <li>[DisconnectCause#REMOTE]</li>
     *                        <li>[DisconnectCause#REJECTED]</li>
     *                        <li>[DisconnectCause#MISSED]</li>
     *                        </ul>
     *
     * @return [CallControlResult.Success] will be returned if Telecom is able to disconnect
     * the call successfully. Otherwise [CallControlResult.Error] will be returned with an error
     * code indicating why disconnect failed.
     */
    suspend fun disconnect(disconnectCause: android.telecom.DisconnectCause): CallControlResult

    /**
     * Request a [CallEndpointCompat] change. Clients should not define their own [CallEndpointCompat] when
     * requesting a change. Instead, the new [endpoint] should be one of the valid [CallEndpointCompat]s
     * provided by [availableEndpoints].
     *
     * @param endpoint The [CallEndpointCompat] to change to.
     *
     * @return [CallControlResult.Success] will be returned if Telecom is able to switch to the
     * requested endpoint successfully.  Otherwise, [CallControlResult.Error] will be returned with
     * an error code indicating why disconnect failed.
     */
    suspend fun requestEndpointChange(endpoint: CallEndpointCompat): CallControlResult

    /**
     * Collect the new [CallEndpointCompat] through which call media flows (i.e. speaker,
     * bluetooth, etc.).
     */
    val currentCallEndpoint: Flow<CallEndpointCompat>

    /**
     * Collect the set of available [CallEndpointCompat]s reported by Telecom.
     */
    val availableEndpoints: Flow<List<CallEndpointCompat>>

    /**
     * Collect the current mute state of the call. This Flow is updated every time the mute state
     * changes.
     */
    val isMuted: Flow<Boolean>
}
