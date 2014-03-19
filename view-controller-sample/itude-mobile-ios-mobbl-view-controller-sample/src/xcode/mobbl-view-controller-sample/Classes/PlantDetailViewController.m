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

//  PlantDetailViewController.m
//  mobbl-view-controller-sample
//  Created by Robin Puthli on 22-02-2014.

#import "PlantDetailViewController.h"

@implementation PlantDetailViewController
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    // we could use self.page.document and self.page.path to retrieve the data programmatically, but it is easier to
    // use the fields in the page definition to bind the data
    _commonName = [self.page firstDescendantOfKind:[MBField class] filterUsingSelector:@selector(name) havingValue:@"commonName"];
    _botanicalName = [self.page firstDescendantOfKind:[MBField class] filterUsingSelector:@selector(name) havingValue:@"botanicalName"];

    // copy the field values into the interface builder objects
    _commonNameTextField.text = [_commonName value];
    _botanicalNameTextField.text = [_botanicalName value];
}


- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

-(IBAction) buttonPressed{
    DLog(@"Saving plant details");
    
    // setting the fields automatically saves the value in the transient Document associated with this page
    [_commonName setValue:_commonNameTextField.text];
    [_botanicalName setValue:_botanicalNameTextField.text];
    
    // save the data. If the document uses the MBFileDataHandler the changes will be permanently stored on disk
    [[MBDataManagerService sharedInstance] storeDocument:self.page.document];
    
    // navigate away
    [self.page handleOutcome:@"OUTCOME-PlantSaved"];
    
}

-(BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [textField resignFirstResponder];
    return YES;
}


@end
