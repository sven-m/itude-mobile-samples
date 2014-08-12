/*
 yeah comments!
 */


#import "CVCApplicationController.h"
#import "CVCApplicationFactory.h"

@implementation CVCApplicationController


@synthesize window = _window;

-(void) startController {
	
	@autoreleasepool {
        // registers a factory that creates custom ViewControllers and Custom Actions
        CVCApplicationFactory *customApplicationFactory = [[CVCApplicationFactory alloc] init];
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
