package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS9990 {
    String WS_ERR_MSG = " ";
    String WS_AC_TYP1 = " ";
    int WS_I = 0;
    short WS_R = 0;
    char WS_ID_TYP1 = ' ';
    String WS_ID_NO1 = " ";
    String WS_CI_NM1 = " ";
    char WS_ID_TYP2 = ' ';
    String WS_ID_NO2 = " ";
    String WS_CI_NM2 = " ";
    String WS_CARD_NO = " ";
    String WS_ACAC_NO = " ";
    double WS_CURR_BAL = 0;
    double WS_ACL_BAL = 0;
    double WS_CURR_BAL2 = 0;
    double WS_ACL_BAL2 = 0;
    double WS_AMT = 0;
    double WS_FEE = 0;
    String WS_ACO_AC1 = " ";
    int WS_OPN_BR1 = 0;
    double WS_CUR_BAL1 = 0;
    double WS_AVL_BAL1 = 0;
    String WS_ACO_AC2 = " ";
    int WS_OPN_BR2 = 0;
    double WS_CUR_BAL2 = 0;
    double WS_AVL_BAL2 = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPACTY BPCPACTY = new BPCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DDCSQIFA DDCSQIFA = new DDCSQIFA();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCUINQV DDCUINQV = new DDCUINQV();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CMCF990 CMCF990 = new CMCF990();
    BPCTCALF BPCTCALF = new BPCTCALF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMB9990_AWA_9990 CMB9990_AWA_9990;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        IBS.init(SCCGWA, SCCFMT);
        CMCF990.REQ_ID = CMB9990_AWA_9990.REQ_ID;
        CMCF990.REQ_STS = CMB9990_AWA_9990.REQ_STS;
        CMCF990.WAT_FLG = CMB9990_AWA_9990.WAT_FLG;
        CMCF990.EODB_FLG = CMB9990_AWA_9990.EODB_FLG;
        CMCF990.EODA_FLG = CMB9990_AWA_9990.EODA_FLG;
        SCCFMT.FMTID = "CM999";
        SCCFMT.DATA_PTR = CMCF990;
        SCCFMT.DATA_LEN = 17;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "CMZS9990 return!");
        Z_RET();
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
