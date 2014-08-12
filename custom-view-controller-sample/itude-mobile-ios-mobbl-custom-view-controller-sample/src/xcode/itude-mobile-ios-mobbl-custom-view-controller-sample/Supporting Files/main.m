/*
yeah comments!
 */

int main(int argc, char *argv[])
{
    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
    int retVal = UIApplicationMain(argc, argv, nil, @"CVCApplicationController");
    [pool release];
    return retVal;
}
