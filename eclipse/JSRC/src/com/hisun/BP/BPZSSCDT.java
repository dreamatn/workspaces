package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCDT {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP196";
    String K_BV_CODE = "00200";
    String K_LOC_CCY = "344";
    char K_STS_DES = '1';
    char K_STS_NOR = '0';
    char K_RES_DES = '1';
    char K_RES_NOR = '0';
    char K_TX_TYPE = '5';
    String CPN_R_ADW_TLSC = "BP-R-ADW-TLSC       ";
    String CPN_R_ADW_TLSB = "BP-R-ADW-TLSB       ";
    String CPN_R_ADW_SCHS = "BP-R-ADW-SCHS       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_CODE_NO = " ";
    int WS_CNT = 0;
    int WS_NUM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSSCDT BPCPSCDT = new BPCSSCDT();
    BPRTLSC BPRTLSC = new BPRTLSC();
    BPRSCHS BPRSCHS = new BPRSCHS();
    BPCOSCDT BPCOSCDT = new BPCOSCDT();
    BPCRSCHS BPCRSCHS = new BPCRSCHS();
    BPCOSCHS BPCOSCHS = new BPCOSCHS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRTLSC BPCRTLSC = new BPCRTLSC();
    BPCRTLSB BPCRTLSB = new BPCRTLSB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCSSCDT BPCSSCDT;
    public void MP(SCCGWA SCCGWA, BPCSSCDT BPCSSCDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCDT = BPCSSCDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSSCDT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, BPCOSCDT);
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRTLSC);
        IBS.init(SCCGWA, BPCRTLSB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPCSSCDT.FUNC == 'N') {
            B010_PAT_RECORD_PROC();
        } else if (BPCSSCDT.FUNC == 'Y') {
            B020_ALL_RECORD_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        B070_CHALK_IT_UP();
        B080_OUTPUT_SCDT_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, BPCSSCDT.FUNC);
        CEP.TRC(SCCGWA, BPCSSCDT.PL_BOX_NO);
    }
    public void B010_PAT_RECORD_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 1000 
            && BPCSSCDT.DATA_INFO[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPRTLSC);
            BPRTLSC.SC_TYPE = BPCSSCDT.DATA_INFO[WS_CNT-1].SC_TYPE;
            BPRTLSC.SC_STS = BPCSSCDT.DATA_INFO[WS_CNT-1].SC_STS;
            BPRTLSC.SC_DATE = BPCSSCDT.DATA_INFO[WS_CNT-1].SC_DATE;
            BPRTLSC.MC_NO = BPCSSCDT.DATA_INFO[WS_CNT-1].MC_NO;
            BPRTLSC.KEY.CODE_NO = BPCSSCDT.DATA_INFO[WS_CNT-1].CODE_NO;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCDT.PL_BOX_NO;
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            BPCRTLSC.FUNC = 'D';
            S000_CALL_BPZRTLSC();
            R000_TRANS_DATA_PARAMETER();
            B060_HISTORY_PROC();
        }
    }
    public void B020_ALL_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        BPCRTLSB.FUNC = 'F';
        BPRTLSC.KEY.PL_BOX_NO = BPCSSCDT.PL_BOX_NO;
        BPRTLSC.SC_STS = '1';
        S000_CALL_BPZRTLSB();
        BPCRTLSB.FUNC = 'R';
        S000_CALL_BPZRTLSB();
        for (WS_CNT = 1; BPCRTLSB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            BPCRTLSC.FUNC = 'D';
            S000_CALL_BPZRTLSC();
            B060_HISTORY_PROC();
            BPCRTLSB.FUNC = 'R';
            S000_CALL_BPZRTLSB();
        }
        BPCRTLSB.FUNC = 'E';
        S000_CALL_BPZRTLSB();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOSCDT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOSCDT.FUNC = BPCSSCDT.FUNC;
        BPCOSCDT.DATA_INFO[WS_CNT-1].SC_DATE = BPRTLSC.SC_DATE;
        BPCOSCDT.DATA_INFO[WS_CNT-1].SC_TYPE = BPRTLSC.SC_TYPE;
        BPCOSCDT.DATA_INFO[WS_CNT-1].SC_STS = '2';
        BPCOSCDT.DATA_INFO[WS_CNT-1].CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPCOSCDT.DATA_INFO[WS_CNT-1].MC_NO = BPRTLSC.MC_NO;
        BPCOSCDT.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOSCDT.TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B080_OUTPUT_SCDT_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOSCDT;
        SCCFMT.DATA_LEN = 1533;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPCRSCHS.FUNC = 'A';
        BPRSCHS.KEY.BR = BPRTLSC.KEY.BR;
        BPRSCHS.PL_BOX_NO = BPRTLSC.KEY.PL_BOX_NO;
        BPRSCHS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRSCHS.SC_DATE = BPRTLSC.SC_DATE;
        BPRSCHS.SC_TYPE = BPRTLSC.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPRSCHS.MC_NO = BPRTLSC.MC_NO;
        BPRSCHS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRSCHS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRSCHS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPRSCHS.TX_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPRSCHS.TX_CODE = "0" + BPRSCHS.TX_CODE;
        BPRSCHS.REC_STS = '0';
        BPRSCHS.TX_TYPE = '5';
        CEP.TRC(SCCGWA, BPCSSCDT.PL_BOX_NO);
        S000_CALL_BPZRSCHS();
    }
    public void B070_CHALK_IT_UP() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B071_SET_EWA_BASIC_INF();
            B072_SET_EWA_EVENTS();
        }
    }
    public void B071_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B072_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BVZUCR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
        WS_NUM = WS_CNT - 1;
        CEP.TRC(SCCGWA, WS_NUM);
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_NUM;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PORT = K_BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = K_BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void S000_CALL_BPZRTLSC() throws IOException,SQLException,Exception {
        BPCRTLSC.POINTER = BPRTLSC;
        BPCRTLSC.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSC, BPCRTLSC);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void S000_CALL_BPZRSCHS() throws IOException,SQLException,Exception {
        BPCRSCHS.POINTER = BPRSCHS;
        BPCRSCHS.LEN = 565;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCHS, BPCRSCHS);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLSB() throws IOException,SQLException,Exception {
        BPCRTLSB.POINTER = BPRTLSC;
        BPCRTLSB.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSB, BPCRTLSB);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
