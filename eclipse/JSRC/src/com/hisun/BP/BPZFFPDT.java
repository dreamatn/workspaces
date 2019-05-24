package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.VT.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFPDT {
    int JIBS_tmp_int;
    DBParm BPTFEHIS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_X = "BPX01";
    String HIS_COPYBOOK = "BPRFPDT";
    int MAX_COL = 99;
    int MAX_ROW = 99;
    int COL_CNT = 18;
    String HIS_REMARK = "TXN INFOMATION MAINTAIN";
    String REC_NHIS = "BP-REC-NHIS         ";
    String R_FPDT = "BP-F-R-FE-UNCHG-DTL";
    String F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String PROC_BPTNHIST = "BP-R-PROC-NHIST";
    String R_FEAG = "BP-F-R-MAINTAIN-FEAG";
    String TBL_FEHIS = "BPTFEHIS";
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFADT BPRFADT = new BPRFADT();
    BPRFPDT BPRFPDT = new BPRFPDT();
    BPRNHIST BPRNHIST = new BPRNHIST();
    DCCURHLD DCCURHLD = new DCCURHLD();
    BPRFEAG BPRFEAG = new BPRFEAG();
    BPCRFEAG BPCRFEAG = new BPCRFEAG();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    BPCTHIST BPCTHIST = new BPCTHIST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRFPDT BPCRFPDT = new BPCRFPDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    VTCPQTAX_WS_VARIABLES WS_VARIABLES = new VTCPQTAX_WS_VARIABLES();
    VTCPQTAX_WS_NHIST_DATA_N WS_NHIST_DATA_N = new VTCPQTAX_WS_NHIST_DATA_N();
    VTCPQTAX_WS_NHIST_DATA_O WS_NHIST_DATA_O = new VTCPQTAX_WS_NHIST_DATA_O();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCSFPDT BPCSFPDT;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    public void MP(SCCGWA SCCGWA, BPCSFPDT BPCSFPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFPDT = BPCSFPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFFPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        IBS.init(SCCGWA, BPCRFPDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFPDT.FUNC);
        CEP.TRC(SCCGWA, BPCSFPDT.FUNC_PROC);
        if (BPCSFPDT.FUNC == 'A') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B200_QUERY_PROCESS();
            if (pgmRtn) return;
            B050_CREATE_PROCESS();
            if (pgmRtn) return;
            B600_HISTORY_PROCESS();
            if (pgmRtn) return;
            B500_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFPDT.FUNC == 'U') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B150_MODIFY_PROCESS();
            if (pgmRtn) return;
            B600_HISTORY_PROCESS();
            if (pgmRtn) return;
            B500_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFPDT.FUNC_PROC == 'P') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B250_PAY_PROCESS();
            if (pgmRtn) return;
            B600_HISTORY_PROCESS();
            if (pgmRtn) return;
            B500_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFPDT.FUNC_PROC == 'R') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B250_PAY_PROCESS_REV();
            if (pgmRtn) return;
            B500_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFPDT.FUNC_PROC == 'F') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B350_FREE_PROCESS();
            if (pgmRtn) return;
            B600_HISTORY_PROCESS();
            if (pgmRtn) return;
            B500_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFPDT.FUNC == 'I') {
            B200_QUERY_PROCESS();
            if (pgmRtn) return;
            B500_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFPDT.FUNC == 'B') {
            B300_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B300_SET_BROWSE_TYPE() throws IOException,SQLException,Exception {
        WS_VARIABLES.CHK_STARTBR = 'N';
        if (BPCSFPDT.CHG_AC.trim().length() > 0) {
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = '2';
            CEP.TRC(SCCGWA, "01");
        }
        if (BPCSFPDT.TX_CI.trim().length() > 0) {
            CEP.TRC(SCCGWA, "02");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = '3';
        }
        if (BPCSFPDT.CARD_PSBK_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "03");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = '4';
        }
        if (BPCSFPDT.LAST_TELL.trim().length() > 0) {
            CEP.TRC(SCCGWA, "04");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = '5';
        }
        if (BPCSFPDT.TRT_BR != 0) {
            CEP.TRC(SCCGWA, "05");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = '6';
        }
        if (BPCSFPDT.TRT_BR != 0 
            && BPCSFPDT.CHG_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "06");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = 'A';
        }
        if (BPCSFPDT.TRT_BR != 0 
            && BPCSFPDT.TX_CI.trim().length() > 0) {
            CEP.TRC(SCCGWA, "07");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = 'B';
        }
        if (BPCSFPDT.TRT_BR != 0 
            && BPCSFPDT.CARD_PSBK_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "08");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = 'I';
        }
        if (BPCSFPDT.TRT_BR != 0 
            && BPCSFPDT.LAST_TELL.trim().length() > 0) {
            CEP.TRC(SCCGWA, "09");
            WS_VARIABLES.CHK_STARTBR = 'Y';
            BPCRFPDT.INFO.FUNC = 'D';
        }
        if (WS_VARIABLES.CHK_STARTBR == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_MESS_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        C100_KEY_VALUE_PREPARE();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'Q';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFPDT);
    }
    public void C100_KEY_VALUE_PREPARE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        CEP.TRC(SCCGWA, BPCSFPDT.FDT_TYP);
        BPCRFPDT.INFO.FDT_TYP = BPCSFPDT.FDT_TYP;
        CEP.TRC(SCCGWA, BPCSFPDT.TRT_DT);
        CEP.TRC(SCCGWA, BPCSFPDT.JRN_NO);
        CEP.TRC(SCCGWA, BPCSFPDT.JRN_SEQ);
        if (BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1') {
            BPRFADT.KEY.TRT_DT = BPCSFPDT.TRT_DT;
            BPRFADT.KEY.JRN_NO = BPCSFPDT.JRN_NO;
            BPRFADT.KEY.JRN_SEQ = BPCSFPDT.JRN_SEQ;
        } else {
            BPRFPDT.KEY.TRT_DT = BPCSFPDT.TRT_DT;
            BPRFPDT.KEY.JRN_NO = BPCSFPDT.JRN_NO;
            BPRFPDT.KEY.JRN_SEQ = BPCSFPDT.JRN_SEQ;
        }
    }
    public void B050_CREATE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRFPDT.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "RECORD ALREADY EXISTED!");
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B051_UPD_DATA_NORM();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'C';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'D') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B051_UPD_DATA_NORM() throws IOException,SQLException,Exception {
        if (BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1') {
            BPRFADT.KEY.TRT_DT = BPCSFPDT.TRT_DT;
            BPRFADT.KEY.JRN_NO = BPCSFPDT.JRN_NO;
            BPRFADT.KEY.JRN_SEQ = BPCSFPDT.JRN_SEQ;
            BPRFADT.CHG_AC = BPCSFPDT.CHG_AC;
            BPRFADT.CARD_PSBK_NO = BPCSFPDT.CARD_PSBK_NO;
            BPRFADT.TX_CI = BPCSFPDT.TX_CI;
            BPRFADT.CHG_AC_TY = BPCSFPDT.CHG_AC_TY;
            BPRFADT.FEE_SRC = BPCSFPDT.FEE_SRC;
            BPRFADT.FEE_CODE = BPCSFPDT.FEE_CODE;
            BPRFADT.CCY = BPCSFPDT.CCY;
            BPRFADT.CCY_TYPE = BPCSFPDT.CCY_TYPE;
            BPRFADT.SALE_DATE = BPCSFPDT.SALE_DATE;
            BPRFADT.CHG_BR = BPCSFPDT.CHG_BR;
            BPRFADT.CHG_AMT = BPCSFPDT.CHG_AMT;
            BPRFADT.ACC_RECH_CNT = BPCSFPDT.ACC_RECH_CNT;
            BPRFADT.CUR_OWE_AMT = BPCSFPDT.CUR_OWE_AMT;
            BPRFADT.ACC_CHG_AMT = BPCSFPDT.ACC_CHG_AMT;
            BPRFADT.CMMT_NO = BPCSFPDT.CMMT_NO;
            BPRFADT.BSNS_NO = BPCSFPDT.BSNS_NO;
            BPRFADT.AMO_FLG = BPCSFPDT.AMO_FLG;
            BPRFADT.AMO_SDT = BPCSFPDT.AMO_SDT;
            BPRFADT.AMO_EDT = BPCSFPDT.AMO_EDT;
            BPRFADT.PRC_STS = BPCSFPDT.PRC_STS;
            BPRFADT.CHG_STS = BPCSFPDT.CHG_STS;
            BPRFADT.TRT_CHNL = BPCSFPDT.TRT_CHNL;
            BPRFADT.TRT_BR = BPCSFPDT.TRT_BR;
            BPRFADT.SRC_TR_CD = BPCSFPDT.SRC_TR_CD;
            BPRFADT.SRC_TR_NAME = BPCSFPDT.SRC_TR_NAME;
            BPRFADT.REMARK = BPCSFPDT.REMARK;
            BPRFADT.CREATE_DATE = BPCSFPDT.CREATE_DATE;
            BPRFADT.CREATE_TIME = BPCSFPDT.CREATE_TIME;
            BPRFADT.UPDATE_DATE = BPCSFPDT.UPDATE_DATE;
            BPRFADT.CREATE_TELL = BPCSFPDT.CREATE_TELL;
            BPRFADT.LAST_TELL = BPCSFPDT.LAST_TELL;
            CEP.TRC(SCCGWA, BPRFADT.LAST_TELL);
            BPRFADT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            CEP.TRC(SCCGWA, BPRFADT.SUP_TEL1);
            BPRFADT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            CEP.TRC(SCCGWA, BPRFADT.SUP_TEL2);
        } else {
            BPRFPDT.KEY.TRT_DT = BPCSFPDT.TRT_DT;
            BPRFPDT.KEY.JRN_NO = BPCSFPDT.JRN_NO;
            BPRFPDT.KEY.JRN_SEQ = BPCSFPDT.JRN_SEQ;
            BPRFPDT.CHG_AC = BPCSFPDT.CHG_AC;
            BPRFPDT.CARD_PSBK_NO = BPCSFPDT.CARD_PSBK_NO;
            BPRFPDT.TX_CI = BPCSFPDT.TX_CI;
            BPRFPDT.CHG_AC_TY = BPCSFPDT.CHG_AC_TY;
            BPRFPDT.FEE_SRC = BPCSFPDT.FEE_SRC;
            BPRFPDT.FEE_CODE = BPCSFPDT.FEE_CODE;
            BPRFPDT.CCY = BPCSFPDT.CCY;
            BPRFPDT.CCY_TYPE = BPCSFPDT.CCY_TYPE;
            BPRFPDT.SALE_DATE = BPCSFPDT.SALE_DATE;
            BPRFPDT.CHG_BR = BPCSFPDT.CHG_BR;
            BPRFPDT.CHG_AMT = BPCSFPDT.CHG_AMT;
            BPRFPDT.ACC_RECH_CNT = BPCSFPDT.ACC_RECH_CNT;
            BPRFPDT.CUR_OWE_AMT = BPCSFPDT.CUR_OWE_AMT;
            BPRFPDT.ACC_CHG_AMT = BPCSFPDT.ACC_CHG_AMT;
            BPRFPDT.CMMT_NO = BPCSFPDT.CMMT_NO;
            BPRFPDT.BSNS_NO = BPCSFPDT.BSNS_NO;
            BPRFPDT.AMO_FLG = BPCSFPDT.AMO_FLG;
            BPRFPDT.AMO_SDT = BPCSFPDT.AMO_SDT;
            BPRFPDT.AMO_EDT = BPCSFPDT.AMO_EDT;
            BPRFPDT.PRC_STS = BPCSFPDT.PRC_STS;
            BPRFPDT.CHG_STS = BPCSFPDT.CHG_STS;
            BPRFPDT.TRT_CHNL = BPCSFPDT.TRT_CHNL;
            BPRFPDT.TRT_BR = BPCSFPDT.TRT_BR;
            BPRFPDT.SRC_TR_CD = BPCSFPDT.SRC_TR_CD;
            BPRFPDT.SRC_TR_NAME = BPCSFPDT.SRC_TR_NAME;
            BPRFPDT.REMARK = BPCSFPDT.REMARK;
            BPRFPDT.CREATE_DATE = BPCSFPDT.CREATE_DATE;
            BPRFPDT.CREATE_TIME = BPCSFPDT.CREATE_TIME;
            BPRFPDT.UPDATE_DATE = BPCSFPDT.UPDATE_DATE;
            BPRFPDT.CREATE_TELL = BPCSFPDT.CREATE_TELL;
            BPRFPDT.LAST_TELL = BPCSFPDT.LAST_TELL;
            BPRFPDT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            CEP.TRC(SCCGWA, BPRFPDT.SUP_TEL1);
            BPRFPDT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            CEP.TRC(SCCGWA, BPRFPDT.SUP_TEL2);
        }
    }
    public void B052_UPD_DATA_PAY() throws IOException,SQLException,Exception {
        if (BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1') {
            BPRFADT.ACC_RECH_CNT = (short) (BPRFADT.ACC_RECH_CNT + 1);
            BPRFADT.CUR_OWE_AMT = 0;
            BPRFADT.ACC_CHG_AMT = BPRFADT.CHG_AMT;
            CEP.TRC(SCCGWA, BPRFADT.CHG_AMT);
            CEP.TRC(SCCGWA, BPRFADT.ACC_CHG_AMT);
            BPRFADT.CHG_STS = '2';
            BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRFADT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRFADT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        } else {
            BPRFPDT.ACC_RECH_CNT = (short) (BPRFPDT.ACC_RECH_CNT + 1);
            BPRFPDT.CUR_OWE_AMT = 0;
            BPRFPDT.ACC_CHG_AMT = BPRFPDT.CHG_AMT;
            BPRFPDT.CHG_STS = '2';
            BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRFPDT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRFPDT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        }
    }
    public void B052_UPD_DATA_PAY_REV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNHIST);
        IBS.init(SCCGWA, BPCTHIST);
        CEP.TRC(SCCGWA, BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_JRN_NO);
        BPRNHIST.KEY.AC_DT = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRNHIST.KEY.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPCPNHIS.INFO.AC = WS_VARIABLES.CHG_AC;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        BPCPNHIS.INFO.TX_TOOL = WS_VARIABLES.CARD_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TOOL);
        BPCPNHIS.INFO.CI_NO = WS_VARIABLES.CI_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CI_NO);
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPCTHIST.INFO.FUNC = '8';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPCTHIST.INFO.FUNC = '2';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
        while (BPCTHIST.RETURN_INFO != 'N') {
            IBS.init(SCCGWA, WS_NHIST_DATA_O);
            if (BPRNHIST.BLOB_OLD_DATA == null) BPRNHIST.BLOB_OLD_DATA = "";
            JIBS_tmp_int = BPRNHIST.BLOB_OLD_DATA.length();
            for (int i=0;i<10240-JIBS_tmp_int;i++) BPRNHIST.BLOB_OLD_DATA += " ";
            IBS.CPY2CLS(SCCGWA, BPRNHIST.BLOB_OLD_DATA.substring(0, BPRNHIST.FMT_LEN), WS_NHIST_DATA_O);
            CEP.TRC(SCCGWA, BPRNHIST.FMT_LEN);
            CEP.TRC(SCCGWA, WS_NHIST_DATA_O);
            C110_DATA_REVERSAL();
            if (pgmRtn) return;
            BPCRFPDT.INFO.FUNC = 'M';
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
            BPCTHIST.INFO.FUNC = '9';
            BPRNHIST.TX_STS = 'C';
            BPCTHIST.PTR = BPRNHIST;
            BPCTHIST.LEN = 436;
            S000_CALL_BPZTHIST();
            if (pgmRtn) return;
            BPCTHIST.RETURN_INFO = 'N';
        }
        BPCTHIST.PTR = BPRNHIST;
        BPCTHIST.LEN = 436;
        BPCTHIST.INFO.FUNC = '3';
        S000_CALL_BPZTHIST();
        if (pgmRtn) return;
    }
    public void C110_DATA_REVERSAL() throws IOException,SQLException,Exception {
        if (BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1') {
            BPRFADT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRFADT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRFADT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPRFADT.ACC_RECH_CNT = WS_NHIST_DATA_O.ACC_RECH_CNT_O;
            BPRFADT.CUR_OWE_AMT = WS_NHIST_DATA_O.CUR_OWE_AMT_O;
            BPRFADT.ACC_CHG_AMT = WS_NHIST_DATA_O.ACC_CHG_AMT_O;
            BPRFADT.CHG_STS = WS_NHIST_DATA_O.CHG_STS_O;
        } else {
            BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRFPDT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRFPDT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPRFPDT.ACC_RECH_CNT = WS_NHIST_DATA_O.ACC_RECH_CNT_O;
            BPRFPDT.CUR_OWE_AMT = WS_NHIST_DATA_O.CUR_OWE_AMT_O;
            BPRFPDT.ACC_CHG_AMT = WS_NHIST_DATA_O.ACC_CHG_AMT_O;
            BPRFPDT.CHG_STS = WS_NHIST_DATA_O.CHG_STS_O;
        }
        CEP.TRC(SCCGWA, WS_NHIST_DATA_O.ACC_RECH_CNT_O);
        CEP.TRC(SCCGWA, WS_NHIST_DATA_O.CUR_OWE_AMT_O);
        CEP.TRC(SCCGWA, WS_NHIST_DATA_O.ACC_CHG_AMT_O);
        CEP.TRC(SCCGWA, WS_NHIST_DATA_O.CHG_STS_O);
    }
    public void B053_UPD_DATA_FREE() throws IOException,SQLException,Exception {
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            BPRFADT.PRC_STS = BPCSFPDT.PRC_STS;
            BPRFADT.CHG_STS = '3';
            BPRFADT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, BPRFADT.LAST_TELL);
            BPRFADT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            CEP.TRC(SCCGWA, BPRFADT.SUP_TEL1);
            BPRFADT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            CEP.TRC(SCCGWA, BPRFADT.SUP_TEL2);
        } else {
            BPRFPDT.PRC_STS = '1';
            BPRFPDT.CHG_STS = '3';
            BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, BPRFPDT.LAST_TELL);
            BPRFPDT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            CEP.TRC(SCCGWA, BPRFPDT.SUP_TEL1);
            BPRFPDT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            CEP.TRC(SCCGWA, BPRFPDT.SUP_TEL2);
        }
    }
    public void B150_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        C200_UPD_CHECKING();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        B051_UPD_DATA_NORM();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'M';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void B250_PAY_PROCESS() throws IOException,SQLException,Exception {
        C200_UPD_CHECKING();
        if (pgmRtn) return;
        C210_SET_FEE_INFO();
        if (pgmRtn) return;
        C220_FEE_PAY_PROC();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        B052_UPD_DATA_PAY();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'M';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void B250_PAY_PROCESS_REV() throws IOException,SQLException,Exception {
        C201_UPD_CHECKING_REV();
        if (pgmRtn) return;
        C210_SET_FEE_INFO();
        if (pgmRtn) return;
        B052_UPD_DATA_PAY_REV();
        if (pgmRtn) return;
    }
    public void B350_FREE_PROCESS() throws IOException,SQLException,Exception {
        C200_UPD_CHECKING();
        if (pgmRtn) return;
        B053_UPD_DATA_FREE();
        if (pgmRtn) return;
        if (BPCRFPDT.INFO.FDT_TYP == '1') {
            B057_FREE_HOLD_MONEY();
            if (pgmRtn) return;
        }
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'M';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (WS_VARIABLES.CHG_AMT != 0 
            || WS_VARIABLES.VAT_AMT != 0) {
            S000_WRITE_QFMC();
            if (pgmRtn) return;
        }
    }
    public void S000_WRITE_QFMC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.THEIR_AC = WS_VARIABLES.CHG_AC;
        BPCPOEWA.DATA.PROD_CODE = WS_VARIABLES.FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = WS_VARIABLES.PROD_CD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = WS_VARIABLES.CHG_BR;
        BPCPOEWA.DATA.BR_NEW = WS_VARIABLES.CHG_BR;
        BPCPOEWA.DATA.CI_NO = WS_VARIABLES.CI_NO;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_VARIABLES.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.EVENT_CODE = "QFMC";
        BPCPOEWA.DATA.DESC = WS_VARIABLES.REMARK;
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPRFPDT.CHG_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPRFPDT.FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPRFPDT.PRD_CD;
        VTCPQTAX.INPUT_DATA.CI_NO = BPRFPDT.TX_CI;
        VTCPQTAX.INPUT_DATA.CCY = BPRFPDT.CCY;
        VTCPQTAX.INPUT_DATA.YJ_AMT = 0 - BPRFPDT.CUR_OWE_AMT;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.YJ_AMT);
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        WS_VARIABLES.VAT_AMT = 0 - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        BPCPOEWA.DATA.AMT_INFO[07-1].AMT = WS_VARIABLES.VAT_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        BPCPOEWA.DATA.AMT_INFO[11-1].AMT = WS_VARIABLES.CHG_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[11-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[11-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPOEWA.RC);
        }
    }
    public void B057_FREE_HOLD_MONEY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEAG);
        IBS.init(SCCGWA, BPCRFEAG);
        BPRFEAG.KEY.CLT_CI_NO = BPRFADT.TX_CI;
        BPRFEAG.KEY.CLT_AC = BPRFADT.CMMT_NO;
        BPRFEAG.KEY.CLT_TYPE = BPRFADT.FEE_SRC;
        BPRFEAG.KEY.EFF_DATE = BPRFADT.KEY.TRT_DT;
        BPCRFEAG.INFO.FUNC = 'Q';
        S000_CALL_BPZRFEAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFEAG.HLD_NO);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = BPRFEAG.HLD_NO;
        DCCURHLD.DATA.RAMT = BPRFADT.CHG_AMT;
        if (DCCURHLD.DATA.HLD_NO.trim().length() > 0 
            && DCCURHLD.DATA.RAMT != 0) {
            S000_CALL_DCZURHLD();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFEAG() throws IOException,SQLException,Exception {
        BPCRFEAG.INFO.POINTER = BPRFEAG;
        BPCRFEAG.INFO.REC_LEN = 677;
        IBS.CALLCPN(SCCGWA, R_FEAG, BPCRFEAG);
        if (BPCRFEAG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFEAG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void C210_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.PROC_TYPE = '4';
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPRFADT.CHG_AC;
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = BPRFADT.CARD_PSBK_NO;
            BPCFFTXI.TX_DATA.CI_NO = BPRFADT.TX_CI;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPRFADT.CHG_AC_TY;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPRFADT.CCY;
            BPCFFTXI.TX_DATA.CCY_TYPE = BPRFADT.CCY_TYPE;
            BPCFFTXI.TX_DATA.SALE_DATE = BPRFADT.SALE_DATE;
            BPCFFTXI.TX_DATA.FEE_DPTM = BPRFADT.CHG_BR;
            if (BPCSFPDT.FUNC_PROC == 'P') {
                BPCFFTXI.TX_DATA.FEE_CTRT = BPRFADT.CMMT_NO;
            }
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPRFPDT.CHG_AC;
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = BPRFPDT.CARD_PSBK_NO;
            BPCFFTXI.TX_DATA.CI_NO = BPRFPDT.TX_CI;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPRFPDT.CHG_AC_TY;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPRFPDT.CCY;
            BPCFFTXI.TX_DATA.CCY_TYPE = BPRFPDT.CCY_TYPE;
            BPCFFTXI.TX_DATA.SALE_DATE = BPRFPDT.SALE_DATE;
            BPCFFTXI.TX_DATA.FEE_DPTM = BPRFPDT.CHG_BR;
            if (BPCSFPDT.FUNC_PROC == 'P') {
                BPCFFTXI.TX_DATA.FEE_CTRT = BPRFPDT.CMMT_NO;
            }
        }
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void C220_FEE_PAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT += 1;
        WS_VARIABLES.ITM = BPCFFCON.FEE_INFO.FEE_CNT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_FLG = '0';
        if (BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1') {
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_CODE = BPRFADT.FEE_CODE;
            BPCFFCON.FEE_INFO.ACCOUNT_BR = BPRFADT.CHG_BR;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_AC_TY = BPRFADT.CHG_AC_TY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].TO_ACCT_CEN = BPRFADT.CHG_BR;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_AC = BPRFADT.CHG_AC;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_CCY = BPRFADT.CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_BAS = BPRFADT.CHG_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_AMT = BPRFADT.CHG_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_CCY = BPRFADT.CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_BAS = BPRFADT.CHG_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_AMT = BPRFADT.CHG_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].ADJ_AMT = BPRFADT.CHG_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_FLG = BPRFADT.AMO_FLG;
            BPCFFCON.FEE_INFO.VAT_AMT = BPRFADT.VAT_AMT;
            BPCFFCON.FEE_INFO.PROD_CODE1 = BPRFADT.PRD_CD;
            if (BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_CCY.trim().length() > 0) {
                if (!BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_CCY.equalsIgnoreCase(BPRFADT.CCY)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CCY_INF_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_FLG == 'Y') {
                if (BPRFADT.AMO_SDT < SCCGWA.COMM_AREA.AC_DATE) {
                    BPRFADT.AMO_SDT = SCCGWA.COMM_AREA.AC_DATE;
                }
                if (BPRFADT.AMO_EDT < SCCGWA.COMM_AREA.AC_DATE) {
                    BPRFADT.AMO_EDT = SCCGWA.COMM_AREA.AC_DATE;
                }
            }
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_STDT = BPRFADT.AMO_SDT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_EDDT = BPRFADT.AMO_EDT;
        } else {
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_CODE = BPRFPDT.FEE_CODE;
            BPCFFCON.FEE_INFO.ACCOUNT_BR = BPRFPDT.CHG_BR;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_AC_TY = BPRFPDT.CHG_AC_TY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].TO_ACCT_CEN = BPRFPDT.CHG_BR;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_AC = BPRFPDT.CHG_AC;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_CCY = BPRFPDT.CCY;
            CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_CCY);
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_BAS = BPRFPDT.CUR_OWE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].FEE_AMT = BPRFPDT.CUR_OWE_AMT;
            CEP.TRC(SCCGWA, BPRFPDT.CCY);
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_CCY = BPRFPDT.CCY;
            CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_CCY);
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_BAS = BPRFPDT.CUR_OWE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].CHG_AMT = BPRFPDT.CUR_OWE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].ADJ_AMT = BPRFPDT.CUR_OWE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_FLG = BPRFPDT.AMO_FLG;
            BPCFFCON.FEE_INFO.VAT_AMT = BPRFPDT.VAT_AMT;
            BPCFFCON.FEE_INFO.PROD_CODE1 = BPRFPDT.PRD_CD;
            if (BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_CCY.trim().length() > 0) {
                if (!BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_CCY.equalsIgnoreCase(BPRFPDT.CCY)) {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_CCY_INF_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_FLG == 'Y') {
                if (BPRFPDT.AMO_SDT < SCCGWA.COMM_AREA.AC_DATE) {
                    BPRFPDT.AMO_SDT = SCCGWA.COMM_AREA.AC_DATE;
                }
                if (BPRFPDT.AMO_EDT < SCCGWA.COMM_AREA.AC_DATE) {
                    BPRFPDT.AMO_EDT = SCCGWA.COMM_AREA.AC_DATE;
                }
            }
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_STDT = BPRFPDT.AMO_SDT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_VARIABLES.ITM-1].AMO_EDDT = BPRFPDT.AMO_EDT;
        }
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void C200_UPD_CHECKING() throws IOException,SQLException,Exception {
        C100_KEY_VALUE_PREPARE();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'R';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            WS_VARIABLES.CHG_AC = BPRFADT.CHG_AC;
            WS_VARIABLES.CARD_NO = BPRFADT.CARD_PSBK_NO;
            WS_VARIABLES.CI_NO = BPRFADT.TX_CI;
            if ((BPRFADT.CHG_STS == '2' 
                || BPRFADT.CHG_STS == '3')) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FE_HAS_BEEN_CHG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_VARIABLES.FEE_CODE = BPRFADT.FEE_CODE;
            WS_VARIABLES.PROD_CD = BPRFADT.PRD_CD;
            WS_VARIABLES.CHG_BR = BPRFADT.CHG_BR;
            WS_VARIABLES.CCY = BPRFADT.CCY;
            WS_VARIABLES.CHG_AMT = BPRFADT.CHG_AMT;
            WS_VARIABLES.VAT_AMT = BPRFADT.VAT_AMT;
            WS_VARIABLES.REMARK = BPRFADT.REMARK;
        } else {
            WS_VARIABLES.CHG_AC = BPRFPDT.CHG_AC;
            WS_VARIABLES.CARD_NO = BPRFPDT.CARD_PSBK_NO;
            WS_VARIABLES.CI_NO = BPRFPDT.TX_CI;
            if ((BPRFPDT.CHG_STS == '2' 
                || BPRFPDT.CHG_STS == '3')) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FE_HAS_BEEN_CHG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRFPDT.PRC_STS == '1') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FE_ALRD_UNCHG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_VARIABLES.FEE_CODE = BPRFPDT.FEE_CODE;
            WS_VARIABLES.PROD_CD = BPRFPDT.PRD_CD;
            WS_VARIABLES.CHG_BR = BPRFPDT.CHG_BR;
            WS_VARIABLES.CCY = BPRFPDT.CCY;
            WS_VARIABLES.CHG_AMT = BPRFPDT.CHG_AMT;
            WS_VARIABLES.CUR_OWE_AMT = BPRFPDT.CUR_OWE_AMT;
            CEP.TRC(SCCGWA, WS_VARIABLES.CUR_OWE_AMT);
            WS_VARIABLES.VAT_AMT = BPRFPDT.VAT_AMT;
            WS_VARIABLES.REMARK = BPRFPDT.REMARK;
        }
    }
    public void C201_UPD_CHECKING_REV() throws IOException,SQLException,Exception {
        C100_KEY_VALUE_PREPARE();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'R';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            if (BPRFADT.CHG_STS != '2') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FE_NOT_STLED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (BPRFPDT.CHG_STS != '2') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_FE_NOT_STLED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        CEP.TRC(SCCGWA, BPCSFPDT.ST_DATE);
        CEP.TRC(SCCGWA, BPCSFPDT.ED_DATE);
        BPCRFPDT.INFO.FDT_TYP = BPCSFPDT.FDT_TYP;
        BPCRFPDT.INFO.ST_DATE = BPCSFPDT.ST_DATE;
        BPCRFPDT.INFO.ED_DATE = BPCSFPDT.ED_DATE;
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            BPRFADT.CHG_AC = BPCSFPDT.CHG_AC;
            BPRFADT.TX_CI = BPCSFPDT.TX_CI;
            BPRFADT.CARD_PSBK_NO = BPCSFPDT.CARD_PSBK_NO;
            BPRFADT.TRT_BR = BPCSFPDT.TRT_BR;
            BPRFADT.LAST_TELL = BPCSFPDT.LAST_TELL;
        } else {
            BPRFPDT.CHG_AC = BPCSFPDT.CHG_AC;
            BPRFPDT.TX_CI = BPCSFPDT.TX_CI;
            BPRFPDT.CARD_PSBK_NO = BPCSFPDT.CARD_PSBK_NO;
            BPRFPDT.TRT_BR = BPCSFPDT.TRT_BR;
            BPRFPDT.LAST_TELL = BPCSFPDT.LAST_TELL;
        }
        BPCRFPDT.INFO.FUNC = '1';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        B310_01_01_OUT_TITLE();
        if (pgmRtn) return;
        BPCRFPDT.INFO.FUNC = 'N';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        while (BPCRFPDT.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B320_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            S000_CALL_BPZRFPDT();
            if (pgmRtn) return;
        }
        BPCRFPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
    }
    public void B310_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 295;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B320_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_TX_BROWSE);
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            WS_VARIABLES.WS_TX_BROWSE.TRT_DT = BPRFADT.KEY.TRT_DT;
            WS_VARIABLES.WS_TX_BROWSE.JRN_NO = BPRFADT.KEY.JRN_NO;
            WS_VARIABLES.WS_TX_BROWSE.JRN_SEQ = BPRFADT.KEY.JRN_SEQ;
            WS_VARIABLES.WS_TX_BROWSE.CHG_AC = BPRFADT.CHG_AC;
            WS_VARIABLES.WS_TX_BROWSE.CARD_NO = BPRFADT.CARD_PSBK_NO;
            WS_VARIABLES.WS_TX_BROWSE.TX_CI = BPRFADT.TX_CI;
            WS_VARIABLES.WS_TX_BROWSE.CHG_AC_TY = BPRFADT.CHG_AC_TY;
            WS_VARIABLES.WS_TX_BROWSE.FEE_SRC = BPRFADT.FEE_SRC;
            WS_VARIABLES.WS_TX_BROWSE.FEE_CODE = BPRFADT.FEE_CODE;
            WS_VARIABLES.WS_TX_BROWSE.CCY = BPRFADT.CCY;
            WS_VARIABLES.WS_TX_BROWSE.CCY_TYPE = BPRFADT.CCY_TYPE;
            WS_VARIABLES.WS_TX_BROWSE.SALE_DATE = BPRFADT.SALE_DATE;
            WS_VARIABLES.WS_TX_BROWSE.CHG_BR = BPRFADT.CHG_BR;
            WS_VARIABLES.WS_TX_BROWSE.CHG_AMT = BPRFADT.CHG_AMT;
            WS_VARIABLES.WS_TX_BROWSE.CUR_OWE_AMT = BPRFADT.CUR_OWE_AMT;
            WS_VARIABLES.WS_TX_BROWSE.CMMT_NO = BPRFADT.CMMT_NO;
            WS_VARIABLES.WS_TX_BROWSE.BSNS_NO = BPRFADT.BSNS_NO;
            CEP.TRC(SCCGWA, BPRFADT.CMMT_NO);
            CEP.TRC(SCCGWA, BPRFADT.BSNS_NO);
            WS_VARIABLES.WS_TX_BROWSE.TRT_CHNL = BPRFADT.TRT_CHNL;
            WS_VARIABLES.WS_TX_BROWSE.TRT_BR = BPRFADT.TRT_BR;
            WS_VARIABLES.WS_TX_BROWSE.LAST_TELL = BPRFADT.LAST_TELL;
            WS_VARIABLES.WS_TX_BROWSE.SRC_TR_CD = BPRFADT.SRC_TR_CD;
            WS_VARIABLES.WS_TX_BROWSE.SRC_TR_NAME = BPRFADT.SRC_TR_NAME;
            WS_VARIABLES.WS_TX_BROWSE.PRC_STS = BPRFADT.PRC_STS;
            WS_VARIABLES.WS_TX_BROWSE.CHG_STS = BPRFADT.CHG_STS;
        } else {
            WS_VARIABLES.WS_TX_BROWSE.TRT_DT = BPRFPDT.KEY.TRT_DT;
            WS_VARIABLES.WS_TX_BROWSE.JRN_NO = BPRFPDT.KEY.JRN_NO;
            WS_VARIABLES.WS_TX_BROWSE.JRN_SEQ = BPRFPDT.KEY.JRN_SEQ;
            WS_VARIABLES.WS_TX_BROWSE.CHG_AC = BPRFPDT.CHG_AC;
            WS_VARIABLES.WS_TX_BROWSE.CARD_NO = BPRFPDT.CARD_PSBK_NO;
            WS_VARIABLES.WS_TX_BROWSE.TX_CI = BPRFPDT.TX_CI;
            WS_VARIABLES.WS_TX_BROWSE.CHG_AC_TY = BPRFPDT.CHG_AC_TY;
            WS_VARIABLES.WS_TX_BROWSE.FEE_SRC = BPRFPDT.FEE_SRC;
            WS_VARIABLES.WS_TX_BROWSE.FEE_CODE = BPRFPDT.FEE_CODE;
            WS_VARIABLES.WS_TX_BROWSE.CCY = BPRFPDT.CCY;
            WS_VARIABLES.WS_TX_BROWSE.CCY_TYPE = BPRFPDT.CCY_TYPE;
            WS_VARIABLES.WS_TX_BROWSE.SALE_DATE = BPRFPDT.SALE_DATE;
            WS_VARIABLES.WS_TX_BROWSE.CHG_BR = BPRFPDT.CHG_BR;
            WS_VARIABLES.WS_TX_BROWSE.CHG_AMT = BPRFPDT.CHG_AMT;
            WS_VARIABLES.WS_TX_BROWSE.CUR_OWE_AMT = BPRFPDT.CUR_OWE_AMT;
            WS_VARIABLES.WS_TX_BROWSE.CMMT_NO = BPRFPDT.CMMT_NO;
            WS_VARIABLES.WS_TX_BROWSE.BSNS_NO = BPRFPDT.BSNS_NO;
            WS_VARIABLES.WS_TX_BROWSE.TRT_CHNL = BPRFPDT.TRT_CHNL;
            WS_VARIABLES.WS_TX_BROWSE.TRT_BR = BPRFPDT.TRT_BR;
            WS_VARIABLES.WS_TX_BROWSE.LAST_TELL = BPRFPDT.LAST_TELL;
            WS_VARIABLES.WS_TX_BROWSE.SRC_TR_CD = BPRFPDT.SRC_TR_CD;
            WS_VARIABLES.WS_TX_BROWSE.SRC_TR_NAME = BPRFPDT.SRC_TR_NAME;
            WS_VARIABLES.WS_TX_BROWSE.PRC_STS = BPRFPDT.PRC_STS;
            WS_VARIABLES.WS_TX_BROWSE.CHG_STS = BPRFPDT.CHG_STS;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TX_BROWSE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TX_BROWSE);
        SCCMPAG.DATA_LEN = 295;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B600_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.OLD_DAT_PT = null;
        BPCPNHIS.INFO.NEW_DAT_PT = null;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 61;
        BPCPNHIS.INFO.AC = WS_VARIABLES.CHG_AC;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        BPCPNHIS.INFO.TX_TOOL = WS_VARIABLES.CARD_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TOOL);
        BPCPNHIS.INFO.CI_NO = WS_VARIABLES.CI_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CI_NO);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID_LEN);
        if (BPCSFPDT.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            IBS.init(SCCGWA, WS_NHIST_DATA_N);
            WS_NHIST_DATA_N.TRT_DT_N = BPRFPDT.KEY.TRT_DT;
            WS_NHIST_DATA_N.JRN_NO_N = BPRFPDT.KEY.JRN_NO;
            WS_NHIST_DATA_N.JRN_SEQ_N = BPRFPDT.KEY.JRN_SEQ;
            WS_NHIST_DATA_N.ACC_RECH_CNT_N = BPRFPDT.ACC_RECH_CNT;
            WS_NHIST_DATA_N.CUR_OWE_AMT_N = BPRFPDT.CUR_OWE_AMT;
            WS_NHIST_DATA_N.ACC_CHG_AMT_N = BPRFPDT.ACC_CHG_AMT;
            WS_NHIST_DATA_N.CHG_STS_N = BPRFPDT.CHG_STS;
            BPCPNHIS.INFO.NEW_DAT_PT = WS_NHIST_DATA_N;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFPDT;
        }
        if (BPCSFPDT.FUNC == 'U' 
            || BPCSFPDT.FUNC_PROC == 'P' 
            || BPCSFPDT.FUNC_PROC == 'F') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = WS_NHIST_DATA_O;
            BPCPNHIS.INFO.NEW_DAT_PT = WS_NHIST_DATA_N;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_SAVE_OLD_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_NHIST_DATA_O);
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            WS_NHIST_DATA_O.TRT_DT_O = BPRFADT.KEY.TRT_DT;
            WS_NHIST_DATA_O.JRN_NO_O = BPRFADT.KEY.JRN_NO;
            WS_NHIST_DATA_O.JRN_SEQ_O = BPRFADT.KEY.JRN_SEQ;
            WS_NHIST_DATA_O.ACC_RECH_CNT_O = BPRFADT.ACC_RECH_CNT;
            WS_NHIST_DATA_O.CUR_OWE_AMT_O = BPRFADT.CUR_OWE_AMT;
            WS_NHIST_DATA_O.ACC_CHG_AMT_O = BPRFADT.ACC_CHG_AMT;
            WS_NHIST_DATA_O.CHG_STS_O = BPRFADT.CHG_STS;
        } else {
            WS_NHIST_DATA_O.TRT_DT_O = BPRFPDT.KEY.TRT_DT;
            WS_NHIST_DATA_O.JRN_NO_O = BPRFPDT.KEY.JRN_NO;
            WS_NHIST_DATA_O.JRN_SEQ_O = BPRFPDT.KEY.JRN_SEQ;
            WS_NHIST_DATA_O.ACC_RECH_CNT_O = BPRFPDT.ACC_RECH_CNT;
            WS_NHIST_DATA_O.CUR_OWE_AMT_O = BPRFPDT.CUR_OWE_AMT;
            WS_NHIST_DATA_O.ACC_CHG_AMT_O = BPRFPDT.ACC_CHG_AMT;
            WS_NHIST_DATA_O.CHG_STS_O = BPRFPDT.CHG_STS;
        }
    }
    public void B061_WRITE_FEE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFEHIS.KEY.JRN_SEQ = BPRFPDT.KEY.JRN_SEQ;
        BPRFEHIS.TX_CI = BPRFPDT.TX_CI;
        BPRFEHIS.TX_PROD = BPRFPDT.PRD_CD;
        BPRFEHIS.FEE_CODE = BPRFPDT.FEE_CODE;
        BPRFEHIS.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
        BPRFEHIS.DRCRFLG = 'C';
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRFEHIS.CHG_BR = BPRFPDT.CHG_BR;
        BPRFEHIS.TX_AC = BPRFPDT.CHG_AC;
        BPRFEHIS.CHG_AC = BPRFPDT.CHG_AC;
        BPRFEHIS.CHG_AC_TY = BPRFPDT.CHG_AC_TY;
        BPRFEHIS.FEE_CCY = BPRFPDT.CCY;
        BPRFEHIS.FEE_BAS = BPRFPDT.CHG_AMT;
        BPRFEHIS.FEE_AMT = BPRFPDT.CHG_AMT;
        BPRFEHIS.CHG_CCY = BPRFPDT.CCY;
        BPRFEHIS.CHG_BAS = BPRFPDT.CHG_AMT;
        BPRFEHIS.CHG_AMT = BPRFPDT.CHG_AMT;
        BPRFEHIS.ADJ_AMT = BPRFPDT.CHG_AMT;
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_BAS);
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_AMT);
        CEP.TRC(SCCGWA, BPRFEHIS.CHG_BAS);
        CEP.TRC(SCCGWA, BPRFEHIS.CHG_AMT);
        CEP.TRC(SCCGWA, BPRFEHIS.ADJ_AMT);
        BPRFEHIS.VAT_AMT = BPRFPDT.VAT_AMT;
        BPRFEHIS.FEE_CTRT = BPRFPDT.CMMT_NO;
        BPRFEHIS.CCY_TYPE = '1';
        BPRFEHIS.BSNS_NO = BPRFPDT.BSNS_NO;
        BPRFEHIS.REMARK = BPRFPDT.REMARK;
        BPRFEHIS.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRFEHIS.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRFEHIS.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFEHIS.TX_STS = 'N';
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRFEHIS.SUP_TEL1 = BPRFPDT.SUP_TEL1;
        BPRFEHIS.SUP_TEL2 = BPRFPDT.SUP_TEL2;
        T000_WRITE_BPTFEHIS();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTFEHIS() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        BPTFEHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_RECORD_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_FEHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B500_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_TX_INQUIRY);
        R510_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TX_INQUIRY);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_TX_INQUIRY;
        SCCFMT.DATA_LEN = 510;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R510_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            WS_VARIABLES.WS_TX_INQUIRY.TRT_DT = BPRFADT.KEY.TRT_DT;
            WS_VARIABLES.WS_TX_INQUIRY.JRN_NO = BPRFADT.KEY.JRN_NO;
            WS_VARIABLES.WS_TX_INQUIRY.JRN_SEQ = BPRFADT.KEY.JRN_SEQ;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_AC = BPRFADT.CHG_AC;
            WS_VARIABLES.WS_TX_INQUIRY.CARD_NO = BPRFADT.CARD_PSBK_NO;
            WS_VARIABLES.WS_TX_INQUIRY.TX_CI = BPRFADT.TX_CI;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_AC_TY = BPRFADT.CHG_AC_TY;
            WS_VARIABLES.WS_TX_INQUIRY.FEE_SRC = BPRFADT.FEE_SRC;
            WS_VARIABLES.WS_TX_INQUIRY.FEE_CODE = BPRFADT.FEE_CODE;
            WS_VARIABLES.WS_TX_INQUIRY.PRD_CD = BPRFADT.PRD_CD;
            WS_VARIABLES.WS_TX_INQUIRY.CCY = BPRFADT.CCY;
            WS_VARIABLES.WS_TX_INQUIRY.CCY_TYPE = BPRFADT.CCY_TYPE;
            WS_VARIABLES.WS_TX_INQUIRY.SALE_DATE = BPRFADT.SALE_DATE;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_BR = BPRFADT.CHG_BR;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_AMT = BPRFADT.CHG_AMT;
            WS_VARIABLES.WS_TX_INQUIRY.ACC_RECH_CNT = BPRFADT.ACC_RECH_CNT;
            WS_VARIABLES.WS_TX_INQUIRY.CUR_OWE_AMT = BPRFADT.CUR_OWE_AMT;
            WS_VARIABLES.WS_TX_INQUIRY.ACC_CHG_AMT = BPRFADT.ACC_CHG_AMT;
            WS_VARIABLES.WS_TX_INQUIRY.CMMT_NO = BPRFADT.CMMT_NO;
            WS_VARIABLES.WS_TX_INQUIRY.BSNS_NO = BPRFADT.BSNS_NO;
            WS_VARIABLES.WS_TX_INQUIRY.AMO_FLG = BPRFADT.AMO_FLG;
            WS_VARIABLES.WS_TX_INQUIRY.AMO_SDT = BPRFADT.AMO_SDT;
            WS_VARIABLES.WS_TX_INQUIRY.AMO_EDT = BPRFADT.AMO_EDT;
            WS_VARIABLES.WS_TX_INQUIRY.PRC_STS = BPRFADT.PRC_STS;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_STS = BPRFADT.CHG_STS;
            WS_VARIABLES.WS_TX_INQUIRY.TRT_CHNL = BPRFADT.TRT_CHNL;
            WS_VARIABLES.WS_TX_INQUIRY.TRT_BR = BPRFADT.TRT_BR;
            WS_VARIABLES.WS_TX_INQUIRY.SRC_TR_CD = BPRFADT.SRC_TR_CD;
            WS_VARIABLES.WS_TX_INQUIRY.SRC_TR_NAME = BPRFADT.SRC_TR_NAME;
            WS_VARIABLES.WS_TX_INQUIRY.REMARK = BPRFADT.REMARK;
            WS_VARIABLES.WS_TX_INQUIRY.CREATE_DATE = BPRFADT.CREATE_DATE;
            WS_VARIABLES.WS_TX_INQUIRY.CREATE_TIME = BPRFADT.CREATE_TIME;
            WS_VARIABLES.WS_TX_INQUIRY.UPDATE_DATE = BPRFADT.UPDATE_DATE;
            WS_VARIABLES.WS_TX_INQUIRY.CREATE_TELL = BPRFADT.CREATE_TELL;
            WS_VARIABLES.WS_TX_INQUIRY.LAST_TELL = BPRFADT.LAST_TELL;
            WS_VARIABLES.WS_TX_INQUIRY.SUP_TEL1 = BPRFADT.SUP_TEL1;
            WS_VARIABLES.WS_TX_INQUIRY.SUP_TEL2 = BPRFADT.SUP_TEL2;
        } else {
            WS_VARIABLES.WS_TX_INQUIRY.TRT_DT = BPRFPDT.KEY.TRT_DT;
            WS_VARIABLES.WS_TX_INQUIRY.JRN_NO = BPRFPDT.KEY.JRN_NO;
            WS_VARIABLES.WS_TX_INQUIRY.JRN_SEQ = BPRFPDT.KEY.JRN_SEQ;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_AC = BPRFPDT.CHG_AC;
            WS_VARIABLES.WS_TX_INQUIRY.CARD_NO = BPRFPDT.CARD_PSBK_NO;
            WS_VARIABLES.WS_TX_INQUIRY.TX_CI = BPRFPDT.TX_CI;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_AC_TY = BPRFPDT.CHG_AC_TY;
            WS_VARIABLES.WS_TX_INQUIRY.FEE_SRC = BPRFPDT.FEE_SRC;
            WS_VARIABLES.WS_TX_INQUIRY.FEE_CODE = BPRFPDT.FEE_CODE;
            WS_VARIABLES.WS_TX_INQUIRY.PRD_CD = BPRFPDT.PRD_CD;
            WS_VARIABLES.WS_TX_INQUIRY.CCY = BPRFPDT.CCY;
            WS_VARIABLES.WS_TX_INQUIRY.CCY_TYPE = BPRFPDT.CCY_TYPE;
            WS_VARIABLES.WS_TX_INQUIRY.SALE_DATE = BPRFPDT.SALE_DATE;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_BR = BPRFPDT.CHG_BR;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_AMT = BPRFPDT.CHG_AMT;
            WS_VARIABLES.WS_TX_INQUIRY.ACC_RECH_CNT = BPRFPDT.ACC_RECH_CNT;
            WS_VARIABLES.WS_TX_INQUIRY.CUR_OWE_AMT = BPRFPDT.CUR_OWE_AMT;
            WS_VARIABLES.WS_TX_INQUIRY.ACC_CHG_AMT = BPRFPDT.ACC_CHG_AMT;
            WS_VARIABLES.WS_TX_INQUIRY.CMMT_NO = BPRFPDT.CMMT_NO;
            WS_VARIABLES.WS_TX_INQUIRY.BSNS_NO = BPRFPDT.BSNS_NO;
            WS_VARIABLES.WS_TX_INQUIRY.AMO_FLG = BPRFPDT.AMO_FLG;
            WS_VARIABLES.WS_TX_INQUIRY.AMO_SDT = BPRFPDT.AMO_SDT;
            WS_VARIABLES.WS_TX_INQUIRY.AMO_EDT = BPRFPDT.AMO_EDT;
            WS_VARIABLES.WS_TX_INQUIRY.PRC_STS = BPRFPDT.PRC_STS;
            WS_VARIABLES.WS_TX_INQUIRY.CHG_STS = BPRFPDT.CHG_STS;
            WS_VARIABLES.WS_TX_INQUIRY.TRT_CHNL = BPRFPDT.TRT_CHNL;
            WS_VARIABLES.WS_TX_INQUIRY.TRT_BR = BPRFPDT.TRT_BR;
            WS_VARIABLES.WS_TX_INQUIRY.SRC_TR_CD = BPRFPDT.SRC_TR_CD;
            WS_VARIABLES.WS_TX_INQUIRY.SRC_TR_NAME = BPRFPDT.SRC_TR_NAME;
            WS_VARIABLES.WS_TX_INQUIRY.REMARK = BPRFPDT.REMARK;
            WS_VARIABLES.WS_TX_INQUIRY.CREATE_DATE = BPRFPDT.CREATE_DATE;
            WS_VARIABLES.WS_TX_INQUIRY.CREATE_TIME = BPRFPDT.CREATE_TIME;
            WS_VARIABLES.WS_TX_INQUIRY.UPDATE_DATE = BPRFPDT.UPDATE_DATE;
            WS_VARIABLES.WS_TX_INQUIRY.CREATE_TELL = BPRFPDT.CREATE_TELL;
            WS_VARIABLES.WS_TX_INQUIRY.LAST_TELL = BPRFPDT.LAST_TELL;
            WS_VARIABLES.WS_TX_INQUIRY.SUP_TEL1 = BPRFPDT.SUP_TEL1;
            WS_VARIABLES.WS_TX_INQUIRY.SUP_TEL2 = BPRFPDT.SUP_TEL2;
        }
    }
    public void S000_CALL_BPZRFPDT() throws IOException,SQLException,Exception {
        if ((BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1')) {
            BPCRFPDT.INFO.POINTER = BPRFADT;
            BPCRFPDT.INFO.LEN = 558;
        } else {
            BPCRFPDT.INFO.POINTER = BPRFPDT;
            BPCRFPDT.INFO.LEN = 558;
        }
        IBS.CALLCPN(SCCGWA, R_FPDT, BPCRFPDT);
        if (BPCRFPDT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_F_CON_CHG_INFO, BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTHIST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, PROC_BPTNHIST, BPCTHIST);
        if (BPCTHIST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTHIST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPNHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        CEP.TRC(SCCGWA, VTCPQTAX.RC);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, VTCPQTAX.OUTPUT_DATA.TAX_AMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
