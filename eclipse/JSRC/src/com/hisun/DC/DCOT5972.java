package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5972 {
    String CPN_DC_S_IR_PRD = "DC-SVR-MPR-CHG";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    short WS_CNT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSMPRC DCCSMPRC = new DCCSMPRC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    DCB5972_AWA_5972 DCB5972_AWA_5972;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5972 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5972_AWA_5972>");
        DCB5972_AWA_5972 = (DCB5972_AWA_5972) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_MPR_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCB5972_AWA_5972.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NO_M_INPUT;
            WS_FLD_NO = DCB5972_AWA_5972.AC_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5972_AWA_5972.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5972_AWA_5972.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5972_AWA_5972.CNTR_INF[1-1].CNTR_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CNTR_NO_M_INPUT;
            WS_FLD_NO = DCB5972_AWA_5972.CNTR_INF[1-1].CNTR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5972_AWA_5972.PROCS_DT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_M_INPUT;
            WS_FLD_NO = DCB5972_AWA_5972.PROCS_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5972_AWA_5972.FUND_TYP != '1' 
            && DCB5972_AWA_5972.FUND_TYP != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUND_TYP_ERR;
            WS_FLD_NO = DCB5972_AWA_5972.FUND_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB5972_AWA_5972.AMTC_MTH != '1' 
            && DCB5972_AWA_5972.AMTC_MTH != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AMTC_MTH_ERR;
            WS_FLD_NO = DCB5972_AWA_5972.AMTC_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void B020_ADD_MPR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSMPRC);
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = DCB5972_AWA_5972.AC_NO;
        DCCPACTY.INPUT.FUNC = '1';
        S000_CALL_DCZPACTY();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
            DCCSMPRC.AC_NO = DCB5972_AWA_5972.AC_NO;
        } else {
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                DCCSMPRC.AC_NO = DCCPACTY.OUTPUT.STD_AC;
            }
        }
        DCCSMPRC.PROD_CODE = DCB5972_AWA_5972.PROD_CD;
        DCCSMPRC.PROCS_DT = DCB5972_AWA_5972.PROCS_DT;
        DCCSMPRC.PROCL_DT = DCB5972_AWA_5972.PROCL_DT;
        DCCSMPRC.SMR = DCB5972_AWA_5972.SMR;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            DCCSMPRC.CNTR_INF[WS_CNT-1].CNTR_NO = DCB5972_AWA_5972.CNTR_INF[WS_CNT-1].CNTR_NO;
            DCCSMPRC.CNTR_INF[WS_CNT-1].ACCT_CNT = DCB5972_AWA_5972.CNTR_INF[WS_CNT-1].ACCT_CNT;
            CEP.TRC(SCCGWA, DCB5972_AWA_5972.CNTR_INF[WS_CNT-1].CNTR_NO);
            CEP.TRC(SCCGWA, DCB5972_AWA_5972.CNTR_INF[WS_CNT-1].ACCT_CNT);
        }
        DCCSMPRC.FUND_TYP = DCB5972_AWA_5972.FUND_TYP;
        DCCSMPRC.AMTC_MTH = DCB5972_AWA_5972.AMTC_MTH;
        DCCSMPRC.COMP_INFO[1-1].AMT_LS = DCB5972_AWA_5972.AMT_LS1;
        DCCSMPRC.COMP_INFO[1-1].AMT_LE = DCB5972_AWA_5972.AMT_LE1;
        DCCSMPRC.COMP_INFO[1-1].PCT_S = DCB5972_AWA_5972.PCT_S1;
        DCCSMPRC.COMP_INFO[2-1].AMT_LS = DCB5972_AWA_5972.AMT_LS2;
        DCCSMPRC.COMP_INFO[2-1].AMT_LE = DCB5972_AWA_5972.AMT_LE2;
        DCCSMPRC.COMP_INFO[2-1].PCT_S = DCB5972_AWA_5972.PCT_S2;
        DCCSMPRC.COMP_INFO[3-1].AMT_LS = DCB5972_AWA_5972.AMT_LS3;
        DCCSMPRC.COMP_INFO[3-1].AMT_LE = DCB5972_AWA_5972.AMT_LE3;
        DCCSMPRC.COMP_INFO[3-1].PCT_S = DCB5972_AWA_5972.PCT_S3;
        DCCSMPRC.COMP_INFO[4-1].AMT_LS = DCB5972_AWA_5972.AMT_LS4;
        DCCSMPRC.COMP_INFO[4-1].AMT_LE = DCB5972_AWA_5972.AMT_LE4;
        DCCSMPRC.COMP_INFO[4-1].PCT_S = DCB5972_AWA_5972.PCT_S4;
        DCCSMPRC.COMP_INFO[5-1].AMT_LS = DCB5972_AWA_5972.AMT_LS5;
        DCCSMPRC.COMP_INFO[5-1].AMT_LE = DCB5972_AWA_5972.AMT_LE5;
        DCCSMPRC.COMP_INFO[5-1].PCT_S = DCB5972_AWA_5972.PCT_S5;
        DCCSMPRC.PROC_ACP = DCB5972_AWA_5972.PROC_ACP;
        DCCSMPRC.LN_PCTS = DCB5972_AWA_5972.LN_PCTS;
        DCCSMPRC.GDD_PCTS = DCB5972_AWA_5972.GDD_PCTS;
        if (DCB5972_AWA_5972.LNK_AC1.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DCB5972_AWA_5972.LNK_AC1;
            DCCPACTY.INPUT.FUNC = '1';
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                DCCSMPRC.AC_INFO[1-1].LNK_AC = DCB5972_AWA_5972.LNK_AC1;
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DCCSMPRC.AC_INFO[1-1].LNK_AC = DCCPACTY.OUTPUT.STD_AC;
                }
            }
        }
        if (DCB5972_AWA_5972.LNK_AC2.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DCB5972_AWA_5972.LNK_AC2;
            DCCPACTY.INPUT.FUNC = '1';
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                DCCSMPRC.AC_INFO[2-1].LNK_AC = DCB5972_AWA_5972.LNK_AC2;
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DCCSMPRC.AC_INFO[2-1].LNK_AC = DCCPACTY.OUTPUT.STD_AC;
                }
            }
        }
        if (DCB5972_AWA_5972.LNK_AC3.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DCB5972_AWA_5972.LNK_AC3;
            DCCPACTY.INPUT.FUNC = '1';
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                DCCSMPRC.AC_INFO[3-1].LNK_AC = DCB5972_AWA_5972.LNK_AC3;
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DCCSMPRC.AC_INFO[3-1].LNK_AC = DCCPACTY.OUTPUT.STD_AC;
                }
            }
        }
        if (DCB5972_AWA_5972.LNK_AC4.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DCB5972_AWA_5972.LNK_AC4;
            DCCPACTY.INPUT.FUNC = '1';
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                DCCSMPRC.AC_INFO[4-1].LNK_AC = DCB5972_AWA_5972.LNK_AC4;
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DCCSMPRC.AC_INFO[4-1].LNK_AC = DCCPACTY.OUTPUT.STD_AC;
                }
            }
        }
        if (DCB5972_AWA_5972.LNK_AC5.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DCB5972_AWA_5972.LNK_AC5;
            DCCPACTY.INPUT.FUNC = '1';
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                DCCSMPRC.AC_INFO[5-1].LNK_AC = DCB5972_AWA_5972.LNK_AC5;
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DCCSMPRC.AC_INFO[5-1].LNK_AC = DCCPACTY.OUTPUT.STD_AC;
                }
            }
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E' 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC_LAST();
        }
        DCCSMPRC.FUNC = '1';
        S000_CALL_DCZSMPRC();
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DCZSMPRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DC_S_IR_PRD, DCCSMPRC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}