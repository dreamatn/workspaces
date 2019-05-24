package com.hisun.EA;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class EAZSOPEN {
    String K_AP_MMO = "EA";
    String K_SYS_ERR = "SC6001";
    int K_OWNER_BR = "706660209";
    String K_OUTPUT_FMT = "EA580";
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    EACMSG_ERROR_MSG EACMSG_ERROR_MSG = new EACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCSWEBD DCCSWEBD = new DCCSWEBD();
    EACO580 EACO580 = new EACO580();
    CICCUST CICCUST = new CICCUST();
    EACSBIND EACSBIND = new EACSBIND();
    SCCGWA SCCGWA;
    EACSOPEN EACSOPEN;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, EACSOPEN EACSOPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EACSOPEN = EACSOPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "OUTCDINT");
        CEP.TRC(SCCGWA, "EAZSOPEN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, EACO580);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSOPEN.CI_NO);
        CEP.TRC(SCCGWA, EACSOPEN.AC_NM);
        CEP.TRC(SCCGWA, EACSOPEN.AC_FLG);
        CEP.TRC(SCCGWA, EACSOPEN.PROD_COD);
        CEP.TRC(SCCGWA, EACSOPEN.AC_TYP);
        CEP.TRC(SCCGWA, EACSOPEN.PSW);
        CEP.TRC(SCCGWA, EACSOPEN.SYS_NO);
        CEP.TRC(SCCGWA, EACSOPEN.POS_LMT);
        CEP.TRC(SCCGWA, EACSOPEN.TRA_LMT);
        CEP.TRC(SCCGWA, EACSOPEN.POS_CNT);
        CEP.TRC(SCCGWA, EACSOPEN.TRA_CNT);
        CEP.TRC(SCCGWA, EACSOPEN.RMK);
        CEP.TRC(SCCGWA, EACSOPEN.IO_FLG);
        CEP.TRC(SCCGWA, EACSOPEN.CON_BNK);
        CEP.TRC(SCCGWA, EACSOPEN.CON_NME);
        CEP.TRC(SCCGWA, EACSOPEN.CON_AC);
        if (EACSOPEN.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.OPEN_CI_NO_INPUT);
        }
        if (EACSOPEN.AC_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.OPEN_AC_NM_INPUT);
        }
        if (EACSOPEN.AC_FLG != '2' 
            && EACSOPEN.AC_FLG != '3') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_OPEN_AC_TYP_MISMATCH);
        }
        if (EACSOPEN.PROD_COD.trim().length() == 0) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_OPEN_PROD_INPUT);
        }
        if (EACSOPEN.AC_TYP == ' ') {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.OPEN_AC_TYP_INPUT);
        }
        if (EACSOPEN.PSW.trim().length() == 0) {
            CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_PSW_MUST_INPUT);
        }
        IBS.init(SCCGWA, DCCSWEBD);
        DCCSWEBD.CI_NO = EACSOPEN.CI_NO;
        DCCSWEBD.AC_NM = EACSOPEN.AC_NM;
        DCCSWEBD.AC_FLG = EACSOPEN.AC_FLG;
        DCCSWEBD.PROD_COD = EACSOPEN.PROD_COD;
        DCCSWEBD.AC_TYP = EACSOPEN.AC_TYP;
        DCCSWEBD.PSW = EACSOPEN.PSW;
        if (EACSOPEN.SYS_NO.trim().length() == 0) {
            DCCSWEBD.SYS_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        } else {
            DCCSWEBD.SYS_NO = EACSOPEN.SYS_NO;
        }
        DCCSWEBD.POS_LMT = EACSOPEN.POS_LMT;
        DCCSWEBD.TRA_LMT = EACSOPEN.TRA_LMT;
        DCCSWEBD.POS_CNT = EACSOPEN.POS_CNT;
        DCCSWEBD.TRA_CNT = EACSOPEN.TRA_CNT;
        DCCSWEBD.RMK = EACSOPEN.RMK;
        DCCSWEBD.IO_FLG = EACSOPEN.IO_FLG;
        DCCSWEBD.CON_AC = EACSOPEN.CON_AC;
        S000_CALL_DCZSWEBD();
        CEP.TRC(SCCGWA, DCCSWEBD.O_CARD_NO);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10210")) {
            B000_BIND_PROC();
        }
        EACO580.CARD_NO = DCCSWEBD.O_CARD_NO;
        EACO580.CI_NO = EACSOPEN.CI_NO;
        EACO580.AC_NM = EACSOPEN.AC_NM;
        EACO580.AC_FLG = EACSOPEN.AC_FLG;
        EACO580.PROD_COD = EACSOPEN.PROD_COD;
        EACO580.AC_TYP = EACSOPEN.AC_TYP;
        EACO580.OWN_BR = 0;
        EACO580.SYS_NO = EACSOPEN.SYS_NO;
        EACO580.POS_LMT = EACSOPEN.POS_LMT;
        EACO580.TRA_LMT = EACSOPEN.TRA_LMT;
        EACO580.POS_CNT = EACSOPEN.POS_CNT;
        EACO580.TRA_CNT = EACSOPEN.TRA_CNT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_LEN = 349;
        SCCFMT.DATA_PTR = EACO580;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B000_BIND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EACSOPEN.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        IBS.init(SCCGWA, EACSBIND);
        EACSBIND.FUNC = 'B';
        EACSBIND.CARD_NO = DCCSWEBD.O_CARD_NO;
        EACSBIND.IO_FLG = EACSOPEN.IO_FLG;
        EACSBIND.CON_BNK = EACSOPEN.CON_BNK;
        EACSBIND.CON_NME = EACSOPEN.CON_NME;
        EACSBIND.CON_AC = EACSOPEN.CON_AC;
        EACSBIND.AC_NM = EACSOPEN.AC_NM;
        EACSBIND.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        EACSBIND.ID_NO = CICCUST.O_DATA.O_ID_NO;
        EACSBIND.PSW = EACSOPEN.PSW;
        S000_CALL_EAZSBIND();
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_EAZSBIND() throws IOException,SQLException,Exception {
        EAZSBIND EAZSBIND = new EAZSBIND();
        EAZSBIND.MP(SCCGWA, EACSBIND);
    }
    public void S000_CALL_DCZSWEBD() throws IOException,SQLException,Exception {
        DCZSWEBD DCZSWEBD = new DCZSWEBD();
        DCZSWEBD.MP(SCCGWA, DCCSWEBD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
