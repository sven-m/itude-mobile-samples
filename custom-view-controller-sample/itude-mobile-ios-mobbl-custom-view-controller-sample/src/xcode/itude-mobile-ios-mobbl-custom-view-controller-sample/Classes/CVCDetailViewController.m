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

#import "CVCDetailViewController.h"
#import "CVCConstants.h"

@interface CVCDetailViewController ()

@property (retain, nonatomic) IBOutlet UILabel *commonNameLabel;
@property (retain, nonatomic) IBOutlet UILabel *botanicalNameLabel;
@property (retain, nonatomic) IBOutlet UILabel *zoneLabel;
@property (retain, nonatomic) IBOutlet UILabel *lightLabel;
@property (retain, nonatomic) IBOutlet UILabel *priceLabel;
@property (retain, nonatomic) IBOutlet UILabel *availabilityLabel;

@end

@implementation CVCDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    MBElement * const element = [self.page.document valueForPath:self.page.absoluteDataPath];
    
    NSString * const commonName = [element valueForPath:CVCConstantsDocumentPathCatalogCommon];
    NSString * const botanicalName = [element valueForPath:CVCConstantsDocumentPathCatalogBotanical];
    NSString * const zone = [element valueForPath:CVCConstantsDocumentPathCatalogZone];
    NSString * const light = [element valueForPath:CVCConstantsDocumentPathCatalogLight];
    NSString * const price = [element valueForPath:CVCConstantsDocumentPathCatalogPrice];
    NSString * const availability = [element valueForPath:CVCConstantsDocumentPathCatalogAvailability];
    
    self.commonNameLabel.text = [NSString stringWithFormat:@"Common name: %@", commonName];
    self.botanicalNameLabel.text = [NSString stringWithFormat:@"Botanical name: %@", botanicalName];
    self.zoneLabel.text = [NSString stringWithFormat:@"Zone: %@", zone];
    self.lightLabel.text = [NSString stringWithFormat:@"Light: %@", light];
    self.priceLabel.text = [NSString stringWithFormat:@"Price: %@", price];
    self.availabilityLabel.text = [NSString stringWithFormat:@"Availability: %@", availability];
}


- (void)dealloc {
    [_commonNameLabel release];
    [_botanicalNameLabel release];
    [_zoneLabel release];
    [_lightLabel release];
    [_priceLabel release];
    [_availabilityLabel release];
    [super dealloc];
}
@end
