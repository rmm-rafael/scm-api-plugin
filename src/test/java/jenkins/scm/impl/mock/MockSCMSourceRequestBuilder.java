/*
 * The MIT License
 *
 * Copyright (c) 2017, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package jenkins.scm.impl.mock;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.model.TaskListener;
import java.util.EnumSet;
import java.util.Set;
import jenkins.scm.api.SCMHeadObserver;
import jenkins.scm.api.SCMSourceCriteria;
import jenkins.scm.api.mixin.ChangeRequestCheckoutStrategy;
import jenkins.scm.api.trait.SCMSourceRequestBuilder;

public class MockSCMSourceRequestBuilder extends SCMSourceRequestBuilder<MockSCMSourceRequestBuilder, MockSCMSourceRequest> {

    private boolean needsBranches;
    private boolean needsTags;
    private boolean needsChangeRequests;
    private Set<ChangeRequestCheckoutStrategy> checkoutStrategies = EnumSet.noneOf(ChangeRequestCheckoutStrategy.class);

    public MockSCMSourceRequestBuilder(MockSCMSource source, SCMSourceCriteria criteria, SCMHeadObserver observer) {
        super(source, criteria, observer);
    }

    public MockSCMSourceRequestBuilder withBranches(boolean included) {
        needsBranches = needsBranches || included;
        return this;
    }

    public MockSCMSourceRequestBuilder withTags(boolean included) {
        needsTags = needsTags || included;
        return this;
    }

    public MockSCMSourceRequestBuilder withChangeRequests(boolean included) {
        needsChangeRequests = needsChangeRequests || included;
        return this;
    }

    public boolean needsBranches() {
        return needsBranches;
    }

    public boolean needsTags() {
        return needsTags;
    }

    public boolean needsChangeRequests() {
        return needsChangeRequests;
    }

    public Set<ChangeRequestCheckoutStrategy> checkoutStrategies() {
        return checkoutStrategies;
    }

    public MockSCMSourceRequestBuilder withCheckoutStrategies(Set<ChangeRequestCheckoutStrategy> checkoutStrategies) {
        this.checkoutStrategies.addAll(checkoutStrategies);
        return this;
    }

    @Override
    public MockSCMSource source() {
        return (MockSCMSource) super.source();
    }

    @Override
    public MockSCMSourceRequest build(@NonNull TaskListener listener) {
        return new MockSCMSourceRequest(this, listener);
    }
}
