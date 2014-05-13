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

#import "CustomFileManager.h"

@implementation CustomFileManager

- (NSString *) getPathToExistingFile:(NSString*) name {
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
