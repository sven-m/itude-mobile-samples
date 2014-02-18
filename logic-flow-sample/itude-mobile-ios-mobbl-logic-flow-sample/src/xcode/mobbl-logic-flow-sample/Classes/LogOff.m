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

//  LogOff.m
//  mobbl-logic-flow-sample
//  Created by Robin Puthli on 18-02-2014.

#import "LogOff.h"
#import "Session.h"

@implementation LogOff
-(MBOutcome*) execute:(MBDocument *)document withPath:(NSString *)path {
    
    // Logoff and flush the cache
    [[Session sharedInstance] logOff];
    [MBCacheManager expireDocumentForKey:@"CATALOG"];
    
    // Tell the framework where to go next
    MBOutcome *oc = [[[MBOutcome alloc] init] autorelease];
    oc.document = nil;
    oc.transferDocument = FALSE;
    oc.noBackgroundProcessing = TRUE;
    oc.outcomeName = @"OUTCOME-Login";
	return oc;
    
}
@end
