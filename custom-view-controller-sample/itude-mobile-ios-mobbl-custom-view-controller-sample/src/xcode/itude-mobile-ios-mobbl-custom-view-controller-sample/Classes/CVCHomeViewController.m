//
//  HomeViewController.m
//  itude-mobile-ios-mobbl-custom-view-controller-sample
//
//  Created by Sven Meyer on 12/08/14.
//  Copyright (c) 2014 Itude Mobile B.V., The Netherlands. All rights reserved.
//

#import "CVCHomeViewController.h"
#import "CVCConstants.h"

@interface CVCHomeViewController ()

@end

@implementation CVCHomeViewController

- (IBAction)nextPageButtonPressed:(id)sender {
    MBOutcome *outcome = [[MBOutcome alloc] initWithOutcomeName:CVCConstantsOutcomeNameGotoOtherPage document:nil];
    [[MBApplicationController currentInstance] handleOutcome:outcome];
    [outcome release];
}


@end
