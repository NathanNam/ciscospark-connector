/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.spark;

import java.util.Map;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;
import org.mule.api.annotations.lifecycle.Start;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.spark.bean.ApplicationGetResponse;
import org.mule.modules.spark.bean.ApplicationIdGetResponse;
import org.mule.modules.spark.bean.ApplicationIdPutRequest;
import org.mule.modules.spark.bean.ApplicationIdPutResponse;
import org.mule.modules.spark.bean.ApplicationPostRequest;
import org.mule.modules.spark.bean.ApplicationPostResponse;
import org.mule.modules.spark.bean.MembershipsGetResponse;
import org.mule.modules.spark.bean.MembershipsIdGetResponse;
import org.mule.modules.spark.bean.MembershipsIdPutRequest;
import org.mule.modules.spark.bean.MembershipsIdPutResponse;
import org.mule.modules.spark.bean.MembershipsPostRequest;
import org.mule.modules.spark.bean.MembershipsPostResponse;
import org.mule.modules.spark.bean.MessagesGetResponse;
import org.mule.modules.spark.bean.MessagesIdGetResponse;
import org.mule.modules.spark.bean.MessagesPostRequest;
import org.mule.modules.spark.bean.MessagesPostResponse;
import org.mule.modules.spark.bean.PeopleGetResponse;
import org.mule.modules.spark.bean.PeopleIdGetResponse;
import org.mule.modules.spark.bean.RoomsGetResponse;
import org.mule.modules.spark.bean.RoomsIdGetResponse;
import org.mule.modules.spark.bean.RoomsIdPutRequest;
import org.mule.modules.spark.bean.RoomsIdPutResponse;
import org.mule.modules.spark.bean.RoomsPostRequest;
import org.mule.modules.spark.bean.RoomsPostResponse;
import org.mule.modules.spark.bean.StatusResponse;
import org.mule.modules.spark.bean.SubscriptionsGetResponse;
import org.mule.modules.spark.bean.SubscriptionsIdGetResponse;
import org.mule.modules.spark.bean.WebhooksGetResponse;
import org.mule.modules.spark.bean.WebhooksIdGetResponse;
import org.mule.modules.spark.bean.WebhooksIdPutRequest;
import org.mule.modules.spark.bean.WebhooksIdPutResponse;
import org.mule.modules.spark.bean.WebhooksPostRequest;
import org.mule.modules.spark.bean.WebhooksPostResponse;
import org.mule.modules.spark.config.ConnectorConfig;

/**
 * This is Cisco Spark Connector Class
 * @author Surender
 *
 * 
 */

@Connector(name = "CiscoSpark", friendlyName = "CiscoSpark", minMuleVersion = "3.7.2")
@RequiresEnterpriseLicense
@MetaDataScope(DataSenseResolver.class)
public class SparkConnector {

  @Config
  ConnectorConfig config;

  private SparkClient client;

  /**
   * 
   */
@Start
  public void init() {
    setClient(new SparkClient(this));
  }

  /**
   * This method get the list of application
   * @param showSubscriptionCount , To show the subscriptionCount metric in the response, set this value to true
   * @param maxLimit , Limits the number of applications in the response.
   * @return ApplicationGetResponse object
 */
@Processor
  public ApplicationGetResponse getApplicationList(
    @Optional Boolean showSubscriptionCount, @Optional Integer maxLimit) {
    return getClient().getApplicationList(showSubscriptionCount, maxLimit);
  }

  /**
   * This method register the application
   * @param applicationPostRequest , A registered application has its own access token that it uses to perform Spark operations on behalf of another user.
   * @return ApplicationPostResponse object
   */
@Processor
  public ApplicationPostResponse registerApplication(
    ApplicationPostRequest applicationPostRequest) {
    return getClient().registerApplication(applicationPostRequest);
  }

  /**
   * This method get the application by Id
   * @param applicationId , using that applicationId it returns applications
   * @param showSubscriptionCount , To show the subscriptionCount metric in the response, set this value to true
   * @return ApplicationIdGetResponse Object
   */
@Processor
  public ApplicationIdGetResponse getApplicationById(String applicationId,
    @Optional Boolean showSubscriptionCount) {
    return getClient().getApplicationById(applicationId,
      showSubscriptionCount);
  }

  /**
   * This method update applications by applicationId
   * @param applicationId , Using that applicationId  It update the applications
   * @param updateApplication , Updates properties for an application, by application ID.
   * @return ApplicationIdPutResponse Object
   */
@Processor
  public ApplicationIdPutResponse updateApplicationById(String applicationId,
		  @Default("#[payload]") ApplicationIdPutRequest updateApplication) {
    return getClient().updateApplicationById(applicationId,
      updateApplication);
  }

  /**
   * This method delete the application using applicationId
   * @param applicationId , Using that applicationId  It delete the applications
   * @return StatusResponse object for delete application
   */
@Processor
  public StatusResponse deleteApplicationById(String applicationId) {
    return getClient().deleteApplicationById(applicationId);
  }

  /**
   * This method get the Members using roomId , personId ,personEmail
   * @param roomId , The room Id 
   * @param personId , The Person Id
   * @param personEmail , The Person Email
   * @param maxLimit , The maximum limit of membership
   * @return MembershipsGetResponse object
   */
@Processor
  public MembershipsGetResponse getMemberships(@Optional String roomId,
    @Optional String personId, @Optional String personEmail,
    @Optional Integer maxLimit) {
    return getClient().getMemberships(roomId, personId, personEmail,
      maxLimit);
  }

  /**
   * This method add the membership into the room
   * @param membershipsPostRequest , add details for a membership.
   * @return MembershipsPostResponse Object
   */
@Processor
  public MembershipsPostResponse addMemberToRoom(
    MembershipsPostRequest membershipsPostRequest) {
    return getClient().addMemberToRoom(membershipsPostRequest);
  }

  /**
   * This method Shows details for a membership, by membership ID.
   * @param membershipId , Specify the membership ID in the membershipId parameter in the URI
   * @return MembershipsIdGetResponse
   */
@Processor
  public MembershipsIdGetResponse getMembershipById(String membershipId) {
    return getClient().getMembershipById(membershipId);
  }

  /**
   * This method Updates properties for a membership, by membership ID.
   * @param membershipId , Specify the membership ID in the membershipId parameter in the URI.
   * @param membershipsIdPutRequest , Update properties for a membership
   * @return MembershipsIdPutResponse Object
   */
@Processor
  public MembershipsIdPutResponse updateMembershipById(String membershipId,
		  @Default("#[payload]") MembershipsIdPutRequest membershipsIdPutRequest) {
    return getClient().updateMembershipById(membershipId,
      membershipsIdPutRequest);
  }

  /**
   * This method Deletes a membership, by membership ID.
   * @param membershipId , Specify the membership ID in the membershipId parameter in the URI.
   * @return StatusResponse Object
   */
@Processor
  public StatusResponse deleteMembershipById(String membershipId) {
    return getClient().deleteMembershipById(membershipId);
  }

  /**
   * This method Returns a list of messages in a specific room, in descending order by create date.
   * @param roomId , Only return messages for this room
   * @param before , Only return messages sent before this datetime in ISO8601 format
   * @param beforeMessage , Only return messages sent before this message ID
   * @param maxLimit , Only return at most this many messages
   * @return MessagesGetResponse Object
   */
@Processor
  public MessagesGetResponse getMessages(String roomId,
    @Optional String before, @Optional String beforeMessage,
    @Optional Integer maxLimit) {
    return getClient().getMessages(roomId, before, beforeMessage, maxLimit);
  }

  /**This method Post a new message and/or media content into a room.
   * @param messagesPostRequest , Post a new message and/or media content into a room.
   * @return MessagesPostResponse Object
   */
@Processor
  public MessagesPostResponse postMessages(
    MessagesPostRequest messagesPostRequest) {
    return getClient().postMessages(messagesPostRequest);
  }

  /**
   * This method Returns a single message using message Id
   * @param messageId , The ||messageId|| parameter in the URL identifies the message you'd like returned.
   * @return MessagesIdGetResponse Object
   */
@Processor
  public MessagesIdGetResponse getMessagesById(String messageId) {
    return getClient().getMessagesById(messageId);
  }

  /**
   * This method delete a single message using message Id
   * @param messageId , Returns a single message. The ||messageId|| parameter inthe URL identifies the message you'd like to delete. Deleting a messageswill notify all members of the room that the authenticated user deleted themessage. Generally, users can only delete their own messages except for thecase of Moderated Rooms and Org Administrators. See TODO to learn about RoomModeration and how it affects permissions.
   * @return StatusResponse Object
   */
@Processor
  public StatusResponse deleteMessagesById(String messageId) {
    return getClient().deleteMessagesById(messageId);
  }

  /**
   * This method Returns a list of people in your organization
   * @param email , Returns people with this exact email address
   * @param displayName , Returns people whose name starts with this string
   * @param maxLimit , Only return at most this many people
   * @return PeopleGetResponse Object
   */
@Processor
  public PeopleGetResponse getPeople(@Optional String email,
    @Optional String displayName, @Optional Integer maxLimit) {
    return getClient().getPeople(email, displayName, maxLimit);
  }

  /**
   * This method Returns a single person by ID
   * @param uid , Returns a single person by ID
   * @return PeopleIdGetResponse Object
   */
@Processor
  public PeopleIdGetResponse getPeopleById(String uid) {
    return getClient().getPeopleById(uid);
  }

  /**
   * This method Returns a list of rooms. In most cases the results will only contain rooms that the authentiated user is a member of.
   * @param showSipAddress , Include the SIP address for this room for VOIP dialing
   * @param maxLimit , Only return at most this many rooms
   * @return RoomsGetResponse Object
   */
@Processor
  public RoomsGetResponse getRooms(@Optional Boolean showSipAddress,
    @Optional Integer maxLimit) {
    return getClient().getRooms(showSipAddress, maxLimit);
  }

  /**
   * This method Creates a new room.
   * @param roomsPostRequest , Creates a new room. The authenticated user is automatically added as a member of the room. See the Memberships API to learn how to add more people to the room.
   * @return RoomsPostResponse Object
   */
@Processor
  public RoomsPostResponse createRooms(RoomsPostRequest roomsPostRequest) {
    return getClient().createRooms(roomsPostRequest);
  }

  /**
   * This method Returns a single room
   * @param roomId , The ||roomId|| parameter in the URL identifies the room you'd like returned.
   * @return RoomsIdGetResponse Object
   */
@Processor
  public RoomsIdGetResponse getRoomById(String roomId) {
    return getClient().getRoomById(roomId);
  }

  /**
   * This method Used to update a single room's properties.
   * @param roomId , The ||roomId|| parameter in the URL identifies the room you'd like to update.
   * @param roomsIdPutRequest , Used to update a single room's properties. The ||roomId|| parameter in the URL identifies the room you'd like to update.
   * @return RoomsIdPutResponse Object
   */
@Processor
  public RoomsIdPutResponse updateRoomById(String roomId,
		  @Default("#[payload]") RoomsIdPutRequest roomsIdPutRequest) {
    return getClient().updateRoomById(roomId, roomsIdPutRequest);
  }

  /**
   * This method Deletes a single room using room Id
   * @param roomId ,  The ||roomId|| parameter in the URL identifies the room you'd like to delete. Deleting a room will TODO.
   * @return StatusResponse Object
   */
@Processor
  public StatusResponse deleteRoomById(String roomId) {
    return getClient().deleteRoomById(roomId);
  }

  /**
   * This method Returns a list of subscriptions
   * @param personId , Return subscriptions for the specified person
   * @param maxLimit , Only return at most this many subscriptions
   * @return SubscriptionsGetResponse Object
   */
@Processor
  public SubscriptionsGetResponse getSubscriptions(@Default("#[payload]") String personId, 
    @Optional Integer maxLimit) {
    return getClient().getSubscriptions(personId, maxLimit);
  }

  /**
   * This method returns a single subscription by ID
   * @param subscriptionId , Returns a single subscription by ID
   * @return SubscriptionsIdGetResponse Object
   */
@Processor
  public SubscriptionsIdGetResponse getSubscriptionsById(String subscriptionId) {
    return getClient().getSubscriptionsById(subscriptionId);
  }

  /**
   * This method deletes a single subscription by ID
   * @param subscriptionId ,the subscriptionId
   * @return StatusResponse Object
   */
@Processor
  public StatusResponse deleteSubscriptionsById(String subscriptionId) {
    return getClient().deleteSubscriptionsById(subscriptionId);
  }

  /**
   * This method Returns a list of webhooks
   * @param maxLimit , Only return at most this many webhooks
   * @return WebhooksGetResponse Object
   */
@Processor
  public WebhooksGetResponse getWebHooks(@Optional Integer maxLimit) {
    return getClient().getWebHooks(maxLimit);
  }

  /**
   * This method POST to the supplied URL the body of the associated message resource whenever there is a new message in that room.
   * @param webhooksPostRequest , Webhooks are a developer's way of receiving notification from the Spark platform that an event has occurred. For example, when registered for "message:created" events for a specific room, Spark will POST to the supplied URL the body of the associated message resource whenever there is a new message in that room.
   * @return WebhooksPostResponse Object
   */
@Processor
  public WebhooksPostResponse postWebHooks(
    WebhooksPostRequest webhooksPostRequest) {
    return getClient().postWebHooks(webhooksPostRequest);
  }

  /**
   * This method Returns a single webhook by ID. 
   * @param webHookUID , The ||webhookId|| parameter in the URL identifies the webhook you'd like to update.
   * @return WebhooksIdGetResponse Object
   */
@Processor
  public WebhooksIdGetResponse getWebHooksById(String webHookUID) {
    return getClient().getWebHooksById(webHookUID);
  }

  /**
   * This method Updates a single webhook's properties.
   * @param webHookUID , The ||webhookId|| parameter in the URL identifies the webhook you'd like to update.
   * @param webhooksIdPutRequest , Updates a single webhook's properties.The ||webhookId|| parameter in the URL identifies the webhook you'd like to update.
   * @return WebhooksIdPutResponse Object
   */
@Processor
  public WebhooksIdPutResponse updateWebHooksById(String webHookUID, @Default("#[payload]")
    WebhooksIdPutRequest webhooksIdPutRequest) {
    return getClient().updateWebHooksById(webHookUID, webhooksIdPutRequest);
  }

  /**
   * This method Deletes a single webhook
   * @param webHookUID , The ||webhookId|| parameter in the URL identifies the webhook you'd like to delete.
   * @return StatusResponse Object
   */
@Processor
  public StatusResponse deleteWebHooksById(String webHookUID) {
    return getClient().deleteWebHooksById(webHookUID);
  }

  
  /**
   * This method add the Entity using key and entity object as value
   * @param key ,  Key to be used to populate the entity
   * @param entity ,  Map that represents the entity
   * @return Some string
   */
@Processor
  public Map<String, Object> addEntity(@MetaDataKeyParam String key,
    @Default("#[payload]") Map<String, Object> entity) {
    /*
     * USE THE KEY AND THE MAP TO DO SOMETHING
     */
    return entity;
  }

  /**
 * @return ConnectorConfig Object
 */
public ConnectorConfig getConfig() {
    return config;
  }

  /**
 * @param config
 */
public void setConfig(ConnectorConfig config) {
    this.config = config;
  }

  /**
 * @return SparkClient Object
 */
public SparkClient getClient() {
    return client;
  }

  /**
 * @param client
 */
public void setClient(SparkClient client) {
    this.client = client;
  }

}
