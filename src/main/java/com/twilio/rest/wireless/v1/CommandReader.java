/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.wireless.v1;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class CommandReader extends Reader<Command> {
    private String sim;
    private Command.Status status;
    private Command.Direction direction;
    private Command.Transport transport;

    /**
     * The `sid` or `unique_name` of the [Sim
     * resources](https://www.twilio.com/docs/wireless/api/sim-resource) to read..
     *
     * @param sim The sid or unique_name of the Sim resources to read
     * @return this
     */
    public CommandReader setSim(final String sim) {
        this.sim = sim;
        return this;
    }

    /**
     * The status of the resources to read. Can be: `queued`, `sent`, `delivered`,
     * `received`, or `failed`..
     *
     * @param status The status of the resources to read
     * @return this
     */
    public CommandReader setStatus(final Command.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Only return Commands with this direction value..
     *
     * @param direction Only return Commands with this direction value
     * @return this
     */
    public CommandReader setDirection(final Command.Direction direction) {
        this.direction = direction;
        return this;
    }

    /**
     * Only return Commands with this transport value. Can be: `sms` or `ip`..
     *
     * @param transport Only return Commands with this transport value
     * @return this
     */
    public CommandReader setTransport(final Command.Transport transport) {
        this.transport = transport;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Command ResourceSet
     */
    @Override
    public ResourceSet<Command> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Command ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Command> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.WIRELESS.toString(),
            "/v1/Commands",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Command ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Command> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Command> nextPage(final Page<Command> page,
                                  final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.WIRELESS.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Command> previousPage(final Page<Command> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.WIRELESS.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Command Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Command> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Command read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Page.fromJson(
            "commands",
            response.getContent(),
            Command.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (sim != null) {
            request.addQueryParam("Sim", sim);
        }

        if (status != null) {
            request.addQueryParam("Status", status.toString());
        }

        if (direction != null) {
            request.addQueryParam("Direction", direction.toString());
        }

        if (transport != null) {
            request.addQueryParam("Transport", transport.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}