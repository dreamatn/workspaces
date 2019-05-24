package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCBB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP192";
    String K_BV_CODE = "BVCD";
    String K_LOC_CCY = "344";
    char K_TX_TYPE_OUT = '1';
    char K_TX_TYPE_IN = '2';
    char K_RES_NOR = '0';
    String CPN_R_ADW_SCBB = "BP-R-ADW-SCBB       ";
    String CPN_R_ADW_SCHS = "BP-R-ADW-SCHS       ";
    String CPN_R_ADW_TLSC = "BP-R-ADW-TLSC       ";
    String CPN_R_ADW_TLSB = "BP-R-ADW-TLSB       ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_CODE_NO = " ";
    String WS_ERR_INFO = " ";
    String WS_CARD_NO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOSCBB BPCOSCBB = new BPCOSCBB();
    BPCSSCBB BPCPSCBB = new BPCSSCBB();
    BPRTLSC BPRTLSC = new BPRTLSC();
    BPRSCHS BPRSCHS = new BPRSCHS();
    BPCRSCBB BPCRSCBB = new BPCRSCBB();
    BPCRSCHS BPCRSCHS = new BPCRSCHS();
    BPCRTLSC BPCRTLSC = new BPCRTLSC();
    BPCRTLSB BPCRTLSB = new BPCRTLSB();
    BPCOSCHS BPCOSCHS = new BPCOSCHS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCSSCBB BPCSSCBB;
    public void MP(SCCGWA SCCGWA, BPCSSCBB BPCSSCBB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCBB = BPCSSCBB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSCBB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRTLSC);
        IBS.init(SCCGWA, BPCRSCBB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSSCBB.FUNC == 'N') {
            B010_PAT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCSSCBB.FUNC == 'Y') {
            B050_QUR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, BPCSSCBB.FUNC);
        CEP.TRC(SCCGWA, BPCSSCBB.PL_BOX_NO);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCSSCBB.TLR_CHG;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_PAT_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRTLSC);
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            BPRTLSC.KEY.CODE_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO;
            BPRTLSC.SC_DATE = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_DATE;
            BPRTLSC.MC_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].MC_NO;
            BPRTLSC.KEY.CODE_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO;
            BPRTLSC.SC_STS = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_STS;
            BPRTLSC.UPD_TLR = BPCSSCBB.TLR;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCBB.PL_BOX_NO;
            BPRTLSC.KEY.BR = BPCSSCBB.BR;
            CEP.TRC(SCCGWA, BPCSSCBB.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO);
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            if (BPCRTLSC.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_CARD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCRTLSC.FUNC = 'D';
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            BPCRTLSC.FUNC = 'Q';
            BPRTLSC.KEY.BR = BPCSSCBB.BR;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCBB.PL_BOX_CHG;
            BPRTLSC.KEY.CODE_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO;
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            if (BPCRTLSC.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_IS_EXIST;
                WS_CARD_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO;
                S000_ERR_MSG_PROC_INFOR();
                if (pgmRtn) return;
            }
            BPRTLSC.KEY.BR = BPCSSCBB.BR;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCBB.PL_BOX_CHG;
            BPRTLSC.SC_DATE = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_DATE;
            BPRTLSC.SC_TYPE = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_TYPE;
            BPRTLSC.SC_STS = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_STS;
            BPRTLSC.KEY.CODE_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO;
            BPRTLSC.MC_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].MC_NO;
            BPRTLSC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLSC.UPD_TLR = BPCSSCBB.TLR_CHG;
            BPCRTLSC.FUNC = 'A';
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            B060_IN_HISTORY_PROC();
            if (pgmRtn) return;
        }
        B070_OUTPUT_SCBB_RECORD();
        if (pgmRtn) return;
    }
    public void B050_QUR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLSC);
        IBS.init(SCCGWA, BPCRTLSB);
        IBS.init(SCCGWA, BPCRTLSC);
        BPCRTLSB.FUNC = 'U';
        BPRTLSC.KEY.PL_BOX_NO = BPCSSCBB.PL_BOX_NO;
        S000_CALL_BPZRTLSB();
        if (pgmRtn) return;
        BPCRTLSB.FUNC = 'R';
        S000_CALL_BPZRTLSB();
        if (pgmRtn) return;
        if (BPCRTLSB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_CNT = 1; BPCRTLSB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000; WS_CNT += 1) {
            BPCRTLSC.FUNC = 'U';
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            BPCRTLSC.FUNC = 'D';
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            BPCRTLSC.FUNC = 'Q';
            BPRTLSC.KEY.BR = BPRTLSC.KEY.BR;
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCBB.PL_BOX_CHG;
            BPRTLSC.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            if (BPCRTLSC.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CARD_IS_EXIST;
                WS_CARD_NO = BPRTLSC.KEY.CODE_NO;
                S000_ERR_MSG_PROC_INFOR();
                if (pgmRtn) return;
            }
            BPRTLSC.KEY.PL_BOX_NO = BPCSSCBB.PL_BOX_CHG;
            BPRTLSC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLSC.UPD_TLR = BPCSSCBB.TLR_CHG;
            BPCRTLSC.FUNC = 'A';
            S000_CALL_BPZRTLSC();
            if (pgmRtn) return;
            B060_IN_HISTORY_PROC();
            if (pgmRtn) return;
            BPCRTLSB.FUNC = 'R';
            S000_CALL_BPZRTLSB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTLSC.KEY.CODE_NO);
        }
        BPCRTLSB.FUNC = 'E';
        S000_CALL_BPZRTLSB();
        if (pgmRtn) return;
        B070_OUTPUT_SCBB_RECORD();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_SCBB_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOSCBB);
        BPCOSCBB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOSCBB.TLR_CHG = BPCSSCBB.TLR_CHG;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            BPCOSCBB.DATA_INFO[WS_CNT-1].SC_DATE = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_DATE;
            BPCOSCBB.DATA_INFO[WS_CNT-1].SC_TYPE = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_TYPE;
            BPCOSCBB.DATA_INFO[WS_CNT-1].SC_STS = BPCSSCBB.DATA_INFO[WS_CNT-1].SC_STS;
            BPCOSCBB.DATA_INFO[WS_CNT-1].CODE_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].CODE_NO;
            BPCOSCBB.DATA_INFO[WS_CNT-1].MC_NO = BPCSSCBB.DATA_INFO[WS_CNT-1].MC_NO;
        }
        BPCOSCBB.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOSCBB.TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOSCBB;
        SCCFMT.DATA_LEN = 1540;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_OUT_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPCRSCHS.FUNC = 'A';
        CEP.TRC(SCCGWA, BPRTLSC.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLSC.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPRSCHS.KEY.BR = BPRTLSC.KEY.BR;
        BPRSCHS.PL_BOX_NO = BPRTLSC.KEY.PL_BOX_NO;
        BPRSCHS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRSCHS.SC_DATE = BPRTLSC.SC_DATE;
        BPRSCHS.SC_TYPE = BPRTLSC.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPRSCHS.MC_NO = BPRTLSC.MC_NO;
        BPRSCHS.TX_BR = BPRTLSC.KEY.BR;
        BPRSCHS.TX_TLR = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRSCHS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPRSCHS.TX_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPRSCHS.TX_CODE = "0" + BPRSCHS.TX_CODE;
        BPRSCHS.REC_STS = '0';
        BPRSCHS.TX_TYPE = '1';
        S000_CALL_BPZRSCHS();
        if (pgmRtn) return;
    }
    public void B060_IN_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSCHS);
        BPCRSCHS.FUNC = 'A';
        BPRSCHS.KEY.BR = BPRTLSC.KEY.BR;
        BPRSCHS.PL_BOX_NO = BPCSSCBB.PL_BOX_CHG;
        BPRSCHS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRSCHS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRSCHS.SC_DATE = BPRTLSC.SC_DATE;
        BPRSCHS.SC_TYPE = BPRTLSC.SC_TYPE;
        BPRSCHS.KEY.CODE_NO = BPRTLSC.KEY.CODE_NO;
        BPRSCHS.MC_NO = BPRTLSC.MC_NO;
        BPRSCHS.TX_BR = BPRTLSC.KEY.BR;
        BPRSCHS.TX_TLR = BPCSSCBB.TLR_CHG;
        BPRSCHS.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = BPRSCHS.TX_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPRSCHS.TX_CODE = "0" + BPRSCHS.TX_CODE;
        CEP.TRC(SCCGWA, BPRSCHS.KEY.BR);
        CEP.TRC(SCCGWA, BPRSCHS.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.CODE_NO);
        CEP.TRC(SCCGWA, BPRSCHS.KEY.TX_JRN);
        BPRSCHS.REC_STS = '0';
        if (BPCSSCBB.TXTYP == 'Y') {
            BPRSCHS.TX_TYPE = '6';
        } else {
            BPRSCHS.TX_TYPE = '2';
        }
        S000_CALL_BPZRSCHS();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void S000_CALL_BPZRSCBB() throws IOException,SQLException,Exception {
        BPCRSCBB.POINTER = BPRTLSC;
        BPCRSCBB.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCBB, BPCRSCBB);
    }
    public void S000_CALL_BPZRSCHS() throws IOException,SQLException,Exception {
        BPCRSCHS.POINTER = BPRSCHS;
        BPCRSCHS.LEN = 565;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_SCHS, BPCRSCHS);
    }
    public void S000_CALL_BPZRTLSC() throws IOException,SQLException,Exception {
        BPCRTLSC.POINTER = BPRTLSC;
        BPCRTLSC.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSC, BPCRTLSC);
    }
    public void S000_CALL_BPZRTLSB() throws IOException,SQLException,Exception {
        BPCRTLSB.POINTER = BPRTLSC;
        BPCRTLSB.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSB, BPCRTLSB);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_INFOR() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_CARD_NO);
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
