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

//  ApplicationFactory.m
//  mobbl-view-controller-sample
//  Created by Robin Puthli on 22-02-2014.

#import "ApplicationFactory.h"
#import "PlantDetailViewController.h"

@implementation ApplicationFactory
-(MBPage *) createPage:(MBPageDefinition *)definition
			  document:(MBDocument*) document
			  rootPath:(NSString*) rootPath
			 viewState:(MBViewState) viewState
		 withMaxBounds:(CGRect) bounds {
    

    if([@"PAGE-PlantDetail" isEqualToString:definition.name]) {
		PlantDetailViewController *myViewController = [[[PlantDetailViewController alloc] init] autorelease];
		MBPage *page = [[[MBPage alloc] initWithDefinition:definition withViewController:myViewController document:document rootPath:rootPath viewState: viewState]autorelease];
		return page;
	}
    
    
    return [super createPage:definition document:document rootPath:rootPath viewState: viewState withMaxBounds: bounds];
}

@end
