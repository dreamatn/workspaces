package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCMI {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP189";
    String K_BV_CODE = "00200";
    String K_LOC_CCY = "344";
    char K_MAK_MOV_STS = '2';
    char K_NOMK_MOV_STS = '0';
    char K_NORMAL_STS = '0';
    String CPN_R_ADW_SCMI = "BP-R-ADW-SCMI       ";
    String CPN_R_ADW_SCHS = "BP-R-ADW-SCHS       ";
    String CPN_R_ADW_SCOW = "BP-R-ADW-DMOV       ";
    String CPN_R_ADW_TLSC = "BP-R-ADW-TLSC       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_NUM = 0;
    String WS_CARD_NO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRSCHS BPRSCHS = new BPRSCHS();
    BPRTLSC BPRTLSC = new BPRTLSC();
    BPRDMOV BPRDMOV = new BPRDMOV();
    BPCRTLSC BPCRTLSC = new BPCRTLSC();
    BPCRDMOV BPCRDMOV = new BPCRDMOV();
    BPCRSCHS BPCRSCHS = new BPCRSCHS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCOSCMI BPCOSCMI = new BPCOSCMI();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCSSCMI BPCSSCMI;
    public void MP(SCCGWA SCCGWA, BPCSSCMI BPCSSCMI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCMI = BPCSSCMI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSSCMI return!");
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCOSCMI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPCSSCMI.FLG == 'N') {
            B010_PAT_RECORD_PROC();
        } else if (BPCSSCMI.FLG == 'Y') {
            B050_QUR_RECORD_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        B070_CHALK_IT_UP();
        B080_OUTPUT_SCMI_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSCMI.DATA_INFO[1-1].SC_DATE);
        CEP.TRC(SCCGWA, BPCSSCMI.DATA_INFO[1-1].SC_TYPE);
        CEP.TRC(SCCGWA, BPCSSCMI.DATA_INFO[1-1].CODE_NO);
        CEP.TRC(SCCGWA, BPCSSCMI.DATA_INFO[1-1].MC_NO);
    }
    public void B010_PAT_RECORD_PROC() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCSSCMI.DATA_INFO[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            IBS.init(SCCGWA, BPRDMOV);
            IBS.init(SCCGWA, BPRTLSC);
            IBS.init(SCCGWA, BPCRDMOV);
            IBS.init(SCCGWA, BPCRTLSC);
            BPRDMOV.KEY.CONF_NO = BPCSSCMI.DATA_INFO[WS_CNT-1].CONF_NO;
            BPRDMOV.KEY.MOV_DT = BPCSSCMI.DATA_INFO[WS_CNT-1].MOVE_DT;
            BPRDMOV.KEY.CODE_NO = BPCSSCMI.DATA_INFO[WS_CNT-1].CODE_NO;
            CEP.TRC(SCCGWA, BPRDMOV.KEY.CONF_NO);
            CEP.TRC(SCCGWA, BPRDMOV.KEY.MOV_DT);
            CEP.TRC(SCCGWA, BPRDMOV.KEY.CODE_NO);
            BPCRDMOV.FUNC = 'P';
            S000_CALL_BPZRDMOV();
            if (BPCRDMOV.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DMOV_NOTFND;
                WS_CARD_NO = BPCSSCMI.DATA_INFO[WS_CNT-1].CODE_NO;
                S000_ERR_MSG_PROC_INFOR();
            }
            BPRDMOV.MOV_STS = K_MAK_MOV_STS;
            BPRDMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCRDMOV.FUNC = 'M';
            S000_CALL_BPZRDMOV();
            BPCRTLSC.FUNC = 'Q';
            BPRTLSC.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCMI.PL_BOX_NO;
            BPRTLSC.KEY.CODE_NO = BPRDMOV.KEY.CODE_NO;
            S000_CALL_BPZRTLSC();
            if (BPCRTLSC.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_IS_EXIST;
                WS_CARD_NO = BPRDMOV.KEY.CODE_NO;
                S000_ERR_MSG_PROC_INFOR();
            }
            BPCRTLSC.FUNC = 'A';
            BPRTLSC.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCMI.PL_BOX_NO;
            BPRTLSC.SC_DATE = BPRDMOV.SC_DATE;
            BPRTLSC.SC_TYPE = BPRDMOV.SC_TYPE;
            BPRTLSC.KEY.CODE_NO = BPRDMOV.KEY.CODE_NO;
            BPRTLSC.MC_NO = BPRDMOV.MC_NO;
            BPRTLSC.SC_STS = BPRDMOV.SC_STS;
            BPRTLSC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLSC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZRTLSC();
            B060_HISTORY_PROC();
        }
    }
    public void B050_QUR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDMOV);
        IBS.init(SCCGWA, BPCRDMOV);
        BPCRDMOV.FUNC = 'U';
        BPRDMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRDMOV.MOV_STS = K_NOMK_MOV_STS;
        S000_CALL_BPZRDMOV();
        BPCRDMOV.FUNC = 'R';
        S000_CALL_BPZRDMOV();
        if (BPCRDMOV.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DMOV_NOTFND;
            S000_ERR_MSG_PROC();
        }
        for (WS_CNT = 1; BPCRDMOV.RETURN_INFO != 'N' 
            && WS_CNT <= 1000; WS_CNT += 1) {
            IBS.init(SCCGWA, BPRTLSC);
            IBS.init(SCCGWA, BPCRTLSC);
            IBS.init(SCCGWA, BPCRDMOV);
            BPRDMOV.MOV_STS = K_MAK_MOV_STS;
            BPRDMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCRDMOV.FUNC = 'M';
            S000_CALL_BPZRDMOV();
            BPCRTLSC.FUNC = 'Q';
            BPRTLSC.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCMI.PL_BOX_NO;
            BPRTLSC.KEY.CODE_NO = BPRDMOV.KEY.CODE_NO;
            S000_CALL_BPZRTLSC();
            if (BPCRTLSC.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_IS_EXIST;
                WS_CARD_NO = BPRDMOV.KEY.CODE_NO;
                S000_ERR_MSG_PROC_INFOR();
            }
            BPCRTLSC.FUNC = 'A';
            BPRTLSC.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCMI.PL_BOX_NO;
            BPRTLSC.SC_DATE = BPRDMOV.SC_DATE;
            BPRTLSC.SC_TYPE = BPRDMOV.SC_TYPE;
            BPRTLSC.KEY.CODE_NO = BPRDMOV.KEY.CODE_NO;
            BPRTLSC.MC_NO = BPRDMOV.MC_NO;
            BPRTLSC.SC_STS = BPRDMOV.SC_STS;
            BPRTLSC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLSC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZRTLSC();
            B060_HISTORY_PROC();
            BPCRDMOV.FUNC = 'R';
            S000_CALL_BPZRDMOV();
        }
    }
    public void B080_OUTPUT_SCMI_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOSCMI);
        BPCOSCMI.FUNC = BPCSSCMI.FLG;
        BPCOSCMI.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOSCMI.PL_BOX_NO = BPCSSCMI.PL_BOX_NO;
        BPCOSCMI.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        for (WS_CNT1 = 1; WS_CNT1 <= 10 
            && BPCSSCMI.DATA_INFO[WS_CNT1-1].CODE_NO.trim().length() != 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            BPCOSCMI.DATA_INFO[WS_CNT1-1].CODE_NO = BPCSSCMI.DATA_INFO[WS_CNT1-1].CODE_NO;
            BPCOSCMI.DATA_INFO[WS_CNT1-1].MOVE_DT = BPCSSCMI.DATA_INFO[WS_CNT1-1].MOVE_DT;
            BPCOSCMI.DATA_INFO[WS_CNT1-1].SC_DATE = BPCSSCMI.DATA_INFO[WS_CNT1-1].SC_DATE;
            BPCOSCMI.DATA_INFO[WS_CNT1-1].MC_NO = BPCSSCMI.DATA_INFO[WS_CNT1-1].MC_NO;
            BPCOSCMI.DATA_INFO[WS_CNT1-1].SC_TYPE = BPCSSCMI.DATA_INFO[WS_CNT1-1].SC_TYPE;
            BPCOSCMI.DATA_INFO[WS_CNT1-1].CONF_NO = BPCSSCMI.DATA_INFO[WS_CNT1-1].CONF_NO;
            BPCOSCMI.DATA_INFO[WS_CNT1-1].SC_STS = BPCSSCMI.DATA_INFO[WS_CNT1-1].SC_STS;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOSCMI);
        SCCFMT.DATA_PTR = BPCOSCMI;
        SCCFMT.DATA_LEN = 1731;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPCRSCHS.FUNC = 'A';
        BPRSCHS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRSCHS.PL_BOX_NO = BPCSSCMI.PL_BOX_NO;
        BPRSCHS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRSCHS.SC_DATE = BPRDMOV.SC_DATE;
        BPRSCHS.SC_TYPE = BPRDMOV.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPRDMOV.KEY.CODE_NO;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.CODE_NO);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_JRN);
        BPRSCHS.MC_NO = BPRDMOV.MC_NO;
        BPRSCHS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPRSCHS.TX_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPRSCHS.TX_CODE = "0" + BPRSCHS.TX_CODE;
        BPRSCHS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRSCHS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRSCHS.TX_AUTH = " ";
        BPRSCHS.REC_STS = '0';
        BPRSCHS.TX_TYPE = '2';
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
        if (BPCSSCMI.DATA_INFO[1-1].SC_STS == K_NORMAL_STS) {
            BPCPOEWA.DATA.EVENT_CODE = "DR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "BVZUDR";
        }
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
    public void B080_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BPZSSCMI";
        BPCPOEWA.DATA.BR_OLD = BPCSSCMI.BR;
        BPCPOEWA.DATA.BR_NEW = BPCSSCMI.BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = K_LOC_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PORT = K_BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = K_BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRSCHS() throws IOException,SQLException,Exception {
        BPCRSCHS.POINTER = BPRSCHS;
        BPCRSCHS.LEN = 565;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCHS, BPCRSCHS);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRDMOV() throws IOException,SQLException,Exception {
        BPCRDMOV.POINTER = BPRDMOV;
        BPCRDMOV.LEN = 225;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCOW, BPCRDMOV);
    }
    public void S000_CALL_BPZRTLSC() throws IOException,SQLException,Exception {
        BPCRTLSC.POINTER = BPRTLSC;
        BPCRTLSC.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSC, BPCRTLSC);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC_INFOR() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_CARD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
