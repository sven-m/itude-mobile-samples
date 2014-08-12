/*
 yeah comments!
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
