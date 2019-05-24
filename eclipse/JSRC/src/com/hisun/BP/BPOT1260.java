package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1260 {
    String CPN_S_BPZFFPDT = "BP-F-S-FE-UNCHG-DTL";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_U_DCZUCINF = "DC-U-CARD-INF";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FDT_TYP = ' ';
    char WS_PROC_TYPE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFPDT BPCSFPDT = new BPCSFPDT();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1260_AWA_1260 BPB1260_AWA_1260;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1260 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1260_AWA_1260>");
        BPB1260_AWA_1260 = (BPB1260_AWA_1260) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFPDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.FDT_TYP);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.ST_DT);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.ED_DT);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.CHG_AC);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.CI_NO);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.CARD_NO);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.TRT_BR);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.LST_TL);
        WS_FDT_TYP = BPB1260_AWA_1260.FDT_TYP;
        if ((WS_FDT_TYP != '0' 
            && WS_FDT_TYP != '1' 
            && WS_FDT_TYP != '2')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_DTL_TYPE_ERR;
            WS_FLD_NO = BPB1260_AWA_1260.FDT_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1260_AWA_1260.ST_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1260_AWA_1260.ST_DT;
            S000_CALL_SCSSCKDT();
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1260_AWA_1260.ST_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1260_AWA_1260.ED_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1260_AWA_1260.ED_DT;
            S000_CALL_SCSSCKDT();
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1260_AWA_1260.ED_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1260_AWA_1260.ST_DT != 0 
            && BPB1260_AWA_1260.ED_DT != 0 
            && BPB1260_AWA_1260.ST_DT > BPB1260_AWA_1260.ED_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB1260_AWA_1260.ST_DT_NO;
            S000_ERR_MSG_PROC();
        }
        WS_PROC_TYPE = ' ';
        CEP.TRC(SCCGWA, WS_PROC_TYPE);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.CHG_AC);
        if (BPB1260_AWA_1260.CHG_AC.trim().length() > 0) {
            if (WS_PROC_TYPE != ' ') {
                CEP.TRC(SCCGWA, "KA02");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_ONE_NONE;
                WS_FLD_NO = BPB1260_AWA_1260.CHG_AC_NO;
                S000_ERR_MSG_PROC();
            } else {
                WS_PROC_TYPE = 'A';
            }
        }
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.CI_NO);
        if (BPB1260_AWA_1260.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPB1260_AWA_1260.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST_CN();
            CEP.TRC(SCCGWA, CICCUST.RC);
            if (CICCUST.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                WS_FLD_NO = BPB1260_AWA_1260.CI_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_PROC_TYPE != ' ') {
                CEP.TRC(SCCGWA, "KA03");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_ONE_NONE;
                WS_FLD_NO = BPB1260_AWA_1260.CI_NO_NO;
                S000_ERR_MSG_PROC();
            } else {
                WS_PROC_TYPE = 'C';
            }
        }
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.CARD_NO);
        if (BPB1260_AWA_1260.CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = BPB1260_AWA_1260.CARD_NO;
            S000_CALL_DCZUCINF();
            if (DCCUCINF.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
                WS_FLD_NO = BPB1260_AWA_1260.CARD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (WS_PROC_TYPE != ' ') {
                CEP.TRC(SCCGWA, "KA04");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_ONE_NONE;
                WS_FLD_NO = BPB1260_AWA_1260.CARD_NO_NO;
                S000_ERR_MSG_PROC();
            } else {
                WS_PROC_TYPE = 'D';
            }
        }
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.LST_TL);
        if (BPB1260_AWA_1260.LST_TL.trim().length() > 0) {
            if (WS_PROC_TYPE != ' ') {
                CEP.TRC(SCCGWA, "KA05");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_ONE_NONE;
                WS_FLD_NO = BPB1260_AWA_1260.LST_TL_NO;
                S000_ERR_MSG_PROC();
            } else {
                WS_PROC_TYPE = 'T';
            }
        }
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.TRT_BR);
        if (BPB1260_AWA_1260.TRT_BR != 0) {
            if (WS_PROC_TYPE != ' ') {
                CEP.TRC(SCCGWA, WS_PROC_TYPE);
                CEP.TRC(SCCGWA, "KA06");
                if (WS_PROC_TYPE == 'A') {
                    WS_PROC_TYPE = 'R';
                }
                if (WS_PROC_TYPE == 'C') {
                    WS_PROC_TYPE = 'S';
                }
                if (WS_PROC_TYPE == 'D') {
                    WS_PROC_TYPE = 'T';
                }
                if (WS_PROC_TYPE == 'T') {
                    WS_PROC_TYPE = 'U';
                }
            } else {
                WS_PROC_TYPE = 'B';
            }
        }
        CEP.TRC(SCCGWA, WS_PROC_TYPE);
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFPDT);
        BPCSFPDT.FDT_TYP = BPB1260_AWA_1260.FDT_TYP;
        BPCSFPDT.CHG_AC = BPB1260_AWA_1260.CHG_AC;
        BPCSFPDT.TX_CI = BPB1260_AWA_1260.CI_NO;
        BPCSFPDT.CARD_PSBK_NO = BPB1260_AWA_1260.CARD_NO;
        BPCSFPDT.TRT_BR = BPB1260_AWA_1260.TRT_BR;
        BPCSFPDT.LAST_TELL = BPB1260_AWA_1260.LST_TL;
        BPCSFPDT.ST_DATE = BPB1260_AWA_1260.ST_DT;
        BPCSFPDT.ED_DATE = BPB1260_AWA_1260.ED_DT;
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.ST_DT);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.ED_DT);
        CEP.TRC(SCCGWA, BPCSFPDT.CHG_AC);
        CEP.TRC(SCCGWA, BPCSFPDT.TX_CI);
        CEP.TRC(SCCGWA, BPCSFPDT.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCSFPDT.TRT_BR);
        CEP.TRC(SCCGWA, BPCSFPDT.LAST_TELL);
        CEP.TRC(SCCGWA, BPCSFPDT.ST_DATE);
        CEP.TRC(SCCGWA, BPCSFPDT.ED_DATE);
        BPCSFPDT.FUNC = 'B';
        S00_CALL_BPZFFPDT();
    }
    public void S00_CALL_BPZFFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFPDT, BPCSFPDT);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void S000_CALL_CIZCUST_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DCZUCINF, DCCUCINF, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
