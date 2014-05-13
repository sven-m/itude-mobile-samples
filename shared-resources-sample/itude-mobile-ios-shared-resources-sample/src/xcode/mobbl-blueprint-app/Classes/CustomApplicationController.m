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

#import "CustomApplicationController.h"
#import "CustomApplicationFactory.h"
#import "CustomFileManager.h"

@implementation CustomApplicationController


@synthesize window = _window;

-(void) startController {
	
	@autoreleasepool {
        // Used as temporary fix, until MOBBL iOS accepts file paths to XML files that contain the .xml suffix
        [[MBResourceService sharedInstance] setFileManager:[[[CustomFileManager alloc] init] autorelease]];
        
        [[MBMetadataService sharedInstance] setConfigName:@"master_config.xml"];
        [[MBMetadataService sharedInstance] setEndpointsName:@"bp-resources/config/endpoints.xml"];

        // registers a factory that creates custom ViewControllers and Custom Actions
        CustomApplicationFactory *customApplicationFactory = [[CustomApplicationFactory alloc] init];
        [MBApplicationFactory setSharedInstance:customApplicationFactory];
        
        [self performSelectorOnMainThread:@selector(startApplication:) withObject:customApplicationFactory waitUntilDone:YES];

    }
    
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    [self startController];
    
    return YES;
}

- (void)dealloc
{
    [_window release];
    [super dealloc];
}

@end
