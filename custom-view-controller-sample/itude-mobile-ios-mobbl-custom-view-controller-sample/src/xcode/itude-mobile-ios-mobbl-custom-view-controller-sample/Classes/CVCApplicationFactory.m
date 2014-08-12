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

#import "CVCApplicationFactory.h"
#import "CVCConstants.h"
#import "CVCHomeViewController.h"
#import "CVCDefaultViewController.h"


@implementation CVCApplicationFactory

- (MBPage *)createPage:(MBPageDefinition *)definition document:(MBDocument *)document rootPath:(NSString *)rootPath viewState:(MBViewState)viewState withMaxBounds:(CGRect)bounds {
    
    id viewController = nil;
    
    if([CVCConstantsPageNameHome isEqualToString:definition.name]) {
        viewController = [[[CVCHomeViewController alloc] initWithNibName:nil bundle:nil] autorelease];
	}
    
    if (viewController == nil) {
        viewController = [[[CVCDefaultViewController alloc] initWithNibName:nil bundle:nil] autorelease];
    }
    
    MBPage * const page = [[MBPage alloc] initWithDefinition:definition
                                          withViewController:viewController
                                                    document:document
                                                    rootPath:rootPath
                                                   viewState:viewState];
    
    return page;

}

@end
