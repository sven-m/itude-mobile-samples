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

//  CheckCredentials.m
//  mobbl-logic-flow-sample
//  Created by Robin Puthli on 18-02-2014.

#import "CheckCredentials.h"
#import "Session.h"

@implementation CheckCredentials

-(MBOutcome*) execute:(MBDocument *)document withPath:(NSString *)path {

    NSString *user = [document valueForPath:@"Credentials[0]/@user"];
    NSString *password = [document valueForPath:@"Credentials[0]/@password"];

    // create the Outcome with data included in it and return it to the Application Controller
    MBOutcome *oc = [[[MBOutcome alloc] init] autorelease];
    oc.document = document;
    oc.transferDocument = TRUE;
    oc.noBackgroundProcessing = TRUE;

    // simple check agains predefined password
    // obviously you will want to add your own logic here
    if ([@"demo" isEqualToString:[user lowercaseString]] && [@"demo" isEqualToString:[password lowercaseString]]) {
        [(Session *)[Session sharedInstance] logOn];
        oc.outcomeName = @"OUTCOME-LoggedIn";
    }
    else{
        oc.outcomeName = @"OUTCOME-LoginError";
    }
	return oc;
}


@end
