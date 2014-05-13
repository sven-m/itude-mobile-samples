//
//  CustomFileManager.m
//  mobbl-blueprint-app
//
//  Created by Sven Meyer on 12/05/14.
//  Copyright (c) 2014 Itude Mobile B.V., The Netherlands. All rights reserved.
//

#import "CustomFileManager.h"

@implementation CustomFileManager

- (NSString*) getPathToExistingFile:(NSString*) name {
    NSString *fileNameWithXMLSuffix = [NSString stringWithFormat:@"%@.xml", name];
    NSString *fileNameAsIs = name;
    
    // Try documents directory
    if ([self documentsDirectoryContainsFile:fileNameAsIs]) {
        return [self prependDocumentsDirectoryPathToFileName:fileNameAsIs];
    }
    
    // try main bundle directory
    else if ([self bundleDirectoryContainsFile:fileNameAsIs]) {
        return [self prependBundleDirectoryToFileName:fileNameAsIs];
    }
    
    // Try adding .xml extension (documents directory)
    else if ([self documentsDirectoryContainsFile:fileNameWithXMLSuffix]) {
        return [self prependDocumentsDirectoryPathToFileName:fileNameWithXMLSuffix];
    }
    
    // Try adding .xml extension (main bundle directory)
    else if ([self bundleDirectoryContainsFile:fileNameWithXMLSuffix]) {
        return [self prependBundleDirectoryToFileName:fileNameWithXMLSuffix];
    }
    
    // As of current MOBBL functionality, return path as it would be in the main bundle directory.
    else {
        return [self prependBundleDirectoryToFileName:fileNameWithXMLSuffix];
    }
}

- (BOOL)documentsDirectoryContainsFile:(NSString *)fileName {
    BOOL fileExists;
    
    NSString *absolutePath = [self prependDocumentsDirectoryPathToFileName:fileName];
    fileExists = [[NSFileManager defaultManager] fileExistsAtPath:absolutePath];
    
    return fileExists;
}

- (BOOL)bundleDirectoryContainsFile:(NSString *)fileName {
    BOOL fileExists;
    
    NSString *absolutePath = [self prependBundleDirectoryToFileName:fileName];
    fileExists = [[NSFileManager defaultManager] fileExistsAtPath:absolutePath];
    
    return fileExists;
}

- (NSString *)prependDocumentsDirectoryPathToFileName:(NSString *)fileName {
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
	NSString *documentsDirectory = [paths objectAtIndex:0];
	
	NSString *absPath = [documentsDirectory stringByAppendingPathComponent: fileName];
    
    return absPath;
}

- (NSString *)prependBundleDirectoryToFileName:(NSString *)fileName {
    NSString *absPathInBundle = [[[NSBundle mainBundle] bundlePath] stringByAppendingPathComponent: fileName];
    
    return absPathInBundle;
}

@end
