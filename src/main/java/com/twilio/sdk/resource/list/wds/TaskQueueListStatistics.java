package com.twilio.sdk.resource.list.wds;

import com.twilio.sdk.TwilioWdsClient;
import com.twilio.sdk.resource.ListResource;
import com.twilio.sdk.resource.instance.wds.TaskQueueStatistics;

import java.util.Map;

/**
 * TaskQueueListStatistics to work with {@link com.twilio.sdk.resource.instance.wds.TaskQueueStatistics}.
 */
public class TaskQueueListStatistics extends ListResource<TaskQueueStatistics, TwilioWdsClient> {

	private String workspaceSid;

	/**
	 * Instantiates a queue list statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 */
	public TaskQueueListStatistics(final TwilioWdsClient client, final String workspaceSid) {
		super(client);
		this.workspaceSid = workspaceSid;
	}

	/**
	 * Instantiates a queue list statistics.
	 *
	 * @param client the client
	 * @param workspaceSid the workspace sid
	 * @param filters the filters
	 */
	public TaskQueueListStatistics(final TwilioWdsClient client, final String workspaceSid,
	                               final Map<String, String> filters) {
		super(client, filters);
		this.workspaceSid = workspaceSid;
	}

	@Override
	protected TaskQueueStatistics makeNew(final TwilioWdsClient client, final Map<String, Object> params) {
		String queueSid = null;
		if (params != null) {
			queueSid = (String) params.get("task_queue_sid");
		}
		return new TaskQueueStatistics(client, workspaceSid, queueSid, null, params);
	}

	@Override
	protected String getListKey() {
		return "task_queues_statistics";
	}

	@Override
	protected String getResourceLocation() {
		return "/" + TwilioWdsClient.DEFAULT_VERSION + "/Accounts/" + getRequestAccountSid() + "/Workspaces/" +
		       workspaceSid + "/Statistics/TaskQueues";
	}
}