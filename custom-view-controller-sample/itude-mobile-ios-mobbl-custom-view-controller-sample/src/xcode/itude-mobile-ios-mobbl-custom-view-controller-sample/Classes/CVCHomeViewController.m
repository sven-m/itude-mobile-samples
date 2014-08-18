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

#import "CVCHomeViewController.h"
#import "CVCConstants.h"

static NSString * const PlantCellIdentifier = @"PlantCellIdentifier";

@interface CVCHomeViewController () <UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, retain) IBOutlet UITableView *tableView;

- (void)configureCell:(UITableViewCell *)cell forIndexPath:(NSIndexPath *)indexPath;
- (NSArray *)dataItems;
- (MBElement *)dataForIndexPath:(NSIndexPath *)indexPath;

@end

@implementation CVCHomeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:PlantCellIdentifier];
}


- (void)dealloc {
    [_tableView release];
    [super dealloc];
}

#pragma mark - Table view datasource and delegate

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:PlantCellIdentifier forIndexPath:indexPath];
    
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:PlantCellIdentifier];
    }
    
    [self configureCell:cell forIndexPath:indexPath];
    
    return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [[self dataItems] count];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    NSInteger row = indexPath.row;
    
    NSString *itemPath = [NSString stringWithFormat:@"/%@[%d]", CVCConstantsDocumentPathCatalogPlantList, row];
    
    [self.page handleOutcome:CVCConstantsOutcomeNameGotoDetailPage withPathArgument:itemPath];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

#pragma mark - Table view data helper methods

- (void)configureCell:(UITableViewCell *)cell forIndexPath:(NSIndexPath *)indexPath {
    
    MBElement *element = [self dataForIndexPath:indexPath];
    
    cell.textLabel.text = [element valueForPath:CVCConstantsDocumentPathCatalogCommon];
    cell.detailTextLabel.text = [element valueForPath:CVCConstantsDocumentPathCatalogBotanical];
}

- (MBElement *)dataForIndexPath:(NSIndexPath *)indexPath {
    NSArray *dataItems = [self dataItems];
    
    return dataItems[indexPath.row];
}

- (NSArray *)dataItems {
    NSArray *elements = [self.page.document valueForPath:CVCConstantsDocumentPathCatalogPlantList];
    
    return elements;
}

@end
