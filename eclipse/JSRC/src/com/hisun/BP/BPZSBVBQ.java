package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBVBQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "BOXLIB INFOMATION MAINTAIN";
    String K_CPY_BPRBUSE = "BPRBUSE";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_R_ADW_BUSE = "BP-R-ADW-BUSE";
    String CPN_R_BRW_BUSE = "BP-R-BRW-BUSE ";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    char WS_TBL_BANK_FLAG = ' ';
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCRBUSB BPCRBUSB = new BPCRBUSB();
    BPCOBVBO BPCOBVBO = new BPCOBVBO();
    BPCOBVQO BPCOBVQO = new BPCOBVQO();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPRBUSE BPRORIS = new BPRBUSE();
    SCCGWA SCCGWA;
    BPCSBVBQ BPCSBVBQ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBVBQ BPCSBVBQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVBQ = BPCSBVBQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVBQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRBUSE);
        IBS.init(SCCGWA, BPCRBUSB);
        IBS.init(SCCGWA, BPCSBVBQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVBQ.FUNC);
        if (BPCSBVBQ.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVBQ.FUNC == 'B') {
            B020_QUERY_DATA_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "ERR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        BPRBUSE.KEY.BR = BPCSBVBQ.BR;
        BPRBUSE.KEY.BV_CODE = BPCSBVBQ.CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVBQ.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVBQ.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVBQ.END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVBQ.TX_DT;
        BPRBUSE.KEY.TX_JRN = BPCSBVBQ.TX_JRN;
        BPCRBUSE.INFO.POINTER = BPRBUSE;
        BPCRBUSE.INFO.LEN = 189;
        BPCRBUSE.INFO.FUNC = 'Q';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
        if (BPCRBUSE.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BUSE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B020_QUERY_DATA_PROCESS() throws IOException,SQLException,Exception {
        B021_STARTBR_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBUSE);
        B022_READNEXT_PROCESS();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_TXIF_FLAG != 'D') {
            CEP.TRC(SCCGWA, BPRBUSE.KEY.BR);
            CEP.TRC(SCCGWA, "11111");
            if (WS_TBL_TXIF_FLAG == 'N') {
                CEP.TRC(SCCGWA, "22222");
                WS_CNT = WS_CNT + 1;
                if (WS_CNT == 1) {
                    B023_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                B024_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            B022_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        B025_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBVBQ.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOBVQO;
        SCCFMT.DATA_LEN = 115;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCOBVQO);
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBVQO);
        BPCOBVQO.FUNC = BPCSBVBQ.FUNC;
        BPCOBVQO.BR = BPRBUSE.KEY.BR;
        BPCOBVQO.BV_CODE = BPRBUSE.KEY.BV_CODE;
        BPCOBVQO.HEAD_NO = BPRBUSE.KEY.HEAD_NO;
        BPCOBVQO.BEG_NO = BPRBUSE.KEY.BEG_NO;
        BPCOBVQO.END_NO = BPRBUSE.KEY.END_NO;
        BPCOBVQO.TX_DT = BPRBUSE.KEY.TX_DT;
        BPCOBVQO.TX_JRN = BPRBUSE.KEY.TX_JRN;
        BPCOBVQO.TYPE = BPRBUSE.TYPE;
        BPCOBVQO.TX_CODE = BPRBUSE.TX_CODE;
        BPCOBVQO.TX_BR = BPRBUSE.TX_BR;
        BPCOBVQO.TX_TLR = BPRBUSE.TX_TLR;
        BPCOBVQO.TX_AUTH = BPRBUSE.TX_AUTH;
        BPCOBVQO.REC_STS = BPRBUSE.REC_STS;
        BPCOBVQO.STS = BPRBUSE.STS;
    }
    public void B021_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        BPRBUSE.KEY.BR = BPCSBVBQ.BR;
        CEP.TRC(SCCGWA, "11111111");
        CEP.TRC(SCCGWA, BPRBUSE.KEY.BR);
        CEP.TRC(SCCGWA, BPCSBVBQ.BEG_NO);
        CEP.TRC(SCCGWA, BPCSBVBQ.END_NO);
        BPRBUSE.KEY.BV_CODE = BPCSBVBQ.CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVBQ.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVBQ.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVBQ.END_NO;
        BPRBUSE.STS = BPCSBVBQ.STS;
        BPRBUSE.KEY.TX_DT = BPCSBVBQ.TX_DT;
        BPRBUSE.TX_TLR = BPCSBVBQ.TLR;
        BPCRBUSB.INFO.BEG_DT = BPCSBVBQ.BEG_DT;
        BPCRBUSB.INFO.END_DT = BPCSBVBQ.END_DT;
        CEP.TRC(SCCGWA, BPCRBUSB.INFO.BEG_DT);
        CEP.TRC(SCCGWA, BPCRBUSB.INFO.END_DT);
        CEP.TRC(SCCGWA, BPRBUSE.STS);
        CEP.TRC(SCCGWA, BPRBUSE.KEY.BEG_NO);
        CEP.TRC(SCCGWA, BPRBUSE.KEY.END_NO);
        BPCRBUSB.INFO.FUNC = 'B';
        BPCRBUSB.INFO.POINTER = BPRBUSE;
        BPCRBUSB.INFO.LEN = 189;
        S000_CALL_BPZRBUSB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "RBUSB-STARTBR-BR");
        CEP.TRC(SCCGWA, BPCRBUSB.RETURN_INFO);
    }
    public void B022_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRBUSB.INFO.FUNC = 'N';
        BPCRBUSB.INFO.POINTER = BPRBUSE;
        BPCRBUSB.INFO.LEN = 189;
        S000_CALL_BPZRBUSB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRBUSB.RETURN_INFO);
        if (BPCRBUSB.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "TEST200001");
            WS_TBL_TXIF_FLAG = 'N';
        } else if (BPCRBUSB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "TEST200002");
            WS_TBL_TXIF_FLAG = 'D';
        } else {
            CEP.TRC(SCCGWA, "TEST200003");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 588;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B024_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBVBO);
        CEP.TRC(SCCGWA, BPRBUSE.KEY.BR);
        CEP.TRC(SCCGWA, BPRBUSE.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPRBUSE.KEY.BEG_NO);
        CEP.TRC(SCCGWA, BPRBUSE.KEY.END_NO);
        CEP.TRC(SCCGWA, BPRBUSE.KEY.TX_JRN);
        BPCOBVBO.BR = BPRBUSE.KEY.BR;
        BPCOBVBO.BV_CODE = BPRBUSE.KEY.BV_CODE;
        BPCOBVBO.BV_CNM = BPCSBVBQ.BV_CNM;
        BPCOBVBO.HEAD_NO = BPRBUSE.KEY.HEAD_NO;
        BPCOBVBO.BEG_NO = BPRBUSE.KEY.BEG_NO;
        BPCOBVBO.END_NO = BPRBUSE.KEY.END_NO;
        BPCOBVBO.STS = BPRBUSE.STS;
        BPCOBVBO.TX_DT = BPRBUSE.KEY.TX_DT;
        BPCOBVBO.TX_JRN = BPRBUSE.KEY.TX_JRN;
        BPCOBVBO.TX_CODE = BPRBUSE.TX_CODE;
        BPCOBVBO.REC_STS = BPRBUSE.REC_STS;
        BPCOBVBO.TLR = BPRBUSE.TX_TLR;
        BPCOBVBO.DRW_NM = BPRBUSE.DRW_NM;
        BPCOBVBO.DRW_IDT = BPRBUSE.DRW_ID_TYP;
        BPCOBVBO.DRW_IDN = BPRBUSE.DRW_ID_NO;
        CEP.TRC(SCCGWA, BPCOBVBO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBVBO);
        SCCMPAG.DATA_LEN = 588;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B025_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRBUSB.INFO.FUNC = 'E';
        BPCRBUSB.INFO.LEN = 189;
        BPCRBUSB.INFO.POINTER = BPRBUSE;
        S000_CALL_BPZRBUSB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "RBUSB-ENDBR");
        CEP.TRC(SCCGWA, BPCRBUSB.RETURN_INFO);
        if (BPCRBUSB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_BUSE, BPCRBUSE);
        if (BPCRBUSE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRBUSB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BUSE, BPCRBUSB);
        if (BPCRBUSB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
