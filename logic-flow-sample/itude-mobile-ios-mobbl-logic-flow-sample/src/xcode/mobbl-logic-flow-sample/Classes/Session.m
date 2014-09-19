/*
 * (C) Copyright Itude Mobile B.V., The Netherlands.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//  Session.m
//  mobbl-logic-flow-sample
//  Created by Robin Puthli on 18-02-2014.

#import "Session.h"

@implementation Session

- (void) dealloc
{
    self.document = nil;
	[super dealloc];
}

-(MBDocument*) document {
	if(!_document)
        _document = [[[MBDataManagerService sharedInstance] loadDocument:@"SessionState"] retain];
	return _document;
}

- (BOOL)loggedOn {
	MBDocument *sessionState = self.document;
	NSString *loggedIn = [sessionState valueForPath:@"Session[0]/@loggedIn"];
	return  [loggedIn isEqualToString: @"TRUE"];
}

-(void) logOn {
    MBDocument *sessionDoc = [self document];
    [sessionDoc setValue:@"TRUE" forPath:@"Session[0]/@loggedIn"];
    [[MBDataManagerService sharedInstance] storeDocument:sessionDoc];
}

-(void) logOff {
    // clear the session related attributes
    MBDocument *sessionDoc = [self document];
    [sessionDoc setValue:@"FALSE" forPath:@"Session[0]/@loggedIn"];
    [[MBDataManagerService sharedInstance] storeDocument:sessionDoc];
}

@end
