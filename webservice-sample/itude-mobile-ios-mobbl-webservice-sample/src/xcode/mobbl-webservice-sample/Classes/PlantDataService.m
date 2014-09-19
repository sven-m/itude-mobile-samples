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

#import "PlantDataService.h"

@implementation PlantDataService

-(void)setHTTPHeaders:(NSMutableURLRequest *)request withArguments:(MBDocument *)args
{
    // force HTTP Get irrespective of args
    [request setHTTPMethod:@"GET"];
    [request setValue:@"text/xml,application/xml" forHTTPHeaderField:@"Accept"];
    [request setValue:@"text/xml" forHTTPHeaderField:@"Content-Type"];
    [request setValue:@"gzip,deflate" forHTTPHeaderField:@"Accept-Encoding"];
}

@end
