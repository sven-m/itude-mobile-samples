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

//  GetFreshPlants.m
//  mobbl-logic-flow-sample
//  Created by Robin Puthli on 18-02-2014.

#import "GetFreshPlants.h"

@implementation GetFreshPlants
-(MBOutcome*) execute:(MBDocument *)document withPath:(NSString *)path {

    // force the cache to be refreshed, regardless of the defined cache interval
    MBDocument *plants = [[MBDataManagerService sharedInstance] loadFreshDocument:@"CATALOG"];
    MBOutcome *oc = [[[MBOutcome alloc] init] autorelease];
    oc.document = plants;
    oc.transferDocument = TRUE;
    oc.noBackgroundProcessing = TRUE;
    oc.outcomeName = @"OUTCOME-Catalog";
	return oc;
}

@end
