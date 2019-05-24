package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSBVDE {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP157";
    char K_STS_TOBE_DSTR = '1';
    String CPN_U_TLR_BV_RVK = "BP-U-TLR-BV-RVK     ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    String BPZSBVDE_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPZSBVDE_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPZSBVDE_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPZSBVDE_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPZSBVDE_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    int WS_CHECK_NUM1 = 0;
    int WS_CHECK_NUM2 = 0;
    char WS_END_FLG = ' ';
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCO157 BPCO157 = new BPCO157();
    BPCUBVRV BPCUBVRV = new BPCUBVRV();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVDE BPCSBVDE;
    public void MP(SCCGWA SCCGWA, BPCSBVDE BPCSBVDE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVDE = BPCSBVDE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVDE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_BV_DESTROY();
        if (pgmRtn) return;
        B050_REC_NHIS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B060_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBVD);
        IBS.init(SCCGWA, BPCRTBDB);
        BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTBVD.KEY.BV_CODE = BPCSBVDE.BV_CODE;
        BPRTBVD.KEY.STS = K_STS_TOBE_DSTR;
        BPRTBVD.KEY.HEAD_NO = BPCSBVDE.HEAD_NO;
        BPRTBVD.BEG_NO = BPCSBVDE.BEG_NO;
        BPRTBVD.KEY.END_NO = BPCSBVDE.END_NO;
        BPCRTBDB.INFO.FUNC = '3';
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRTBDB.RETURN_INFO);
        if (BPCRTBDB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_REC_DESTROY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCSBVDE.BEG_NO);
            CEP.TRC(SCCGWA, BPCSBVDE.END_NO);
            CEP.TRC(SCCGWA, BPCSBVDE.NUM);
            CEP.TRC(SCCGWA, BPCSBVDE.NUM);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
        }
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
        if (pgmRtn) return;
    }
    public void B020_BV_DESTROY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVRV);
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVDE.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSBVDE.DE_TYP);
        CEP.TRC(SCCGWA, "1234");
        if (BPCSBVDE.DE_TYP == '0') {
            BPCUBVRV.FUNC = '2';
        } else {
            BPCUBVRV.FUNC = '1';
        }
        CEP.TRC(SCCGWA, BPCUBVRV.FUNC);
        BPCUBVRV.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPCUBVRV.VB_FLG = '1';
        BPCUBVRV.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVRV.BV_CODE = BPCSBVDE.BV_CODE;
        BPCUBVRV.HEAD_NO = BPCSBVDE.HEAD_NO;
        BPCUBVRV.BEG_NO = BPCSBVDE.BEG_NO;
        BPCUBVRV.END_NO = BPCSBVDE.END_NO;
        BPCUBVRV.NUM = BPCSBVDE.NUM;
        S000_CALL_BPZUBVRV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "UBVRV-CNT123");
        CEP.TRC(SCCGWA, BPCUBVRV.CNT);
        if (BPCUBVRV.FUNC == '1') {
            B021_BUSE_PROCESS();
            if (pgmRtn) return;
        } else {
            for (WS_I = 1; WS_I <= BPCUBVRV.CNT; WS_I += 1) {
                B021_BUSE_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVDE.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPCFBVQU.TX_DATA.TYPE != '1' 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            B021_ADD_BUSE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B021_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCUBVRV.FUNC == '1') {
            BPRBUSE.KEY.BV_CODE = BPCSBVDE.BV_CODE;
            BPRBUSE.KEY.HEAD_NO = BPCSBVDE.HEAD_NO;
            BPRBUSE.KEY.BEG_NO = BPCSBVDE.BEG_NO;
            BPRBUSE.KEY.END_NO = BPCSBVDE.END_NO;
        } else {
            BPRBUSE.KEY.BV_CODE = BPCUBVRV.DATA_INFO[WS_I-1].BV_CODE1;
            BPRBUSE.KEY.HEAD_NO = BPCUBVRV.DATA_INFO[WS_I-1].HEAD_NO1;
            BPRBUSE.KEY.BEG_NO = BPCUBVRV.DATA_INFO[WS_I-1].BEG_NO1;
            BPRBUSE.KEY.END_NO = BPCUBVRV.DATA_INFO[WS_I-1].END_NO1;
        }
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '2';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (BPCUBVRV.FUNC == '1') {
            WS_HIS_BVCODE = BPCSBVDE.BV_CODE;
            WS_HIS_HEADNO = BPCSBVDE.HEAD_NO;
            WS_HIS_BEGNO = BPCSBVDE.BEG_NO;
            WS_HIS_ENDNO = BPCSBVDE.END_NO;
            WS_HIS_NUMNO = BPCSBVDE.NUM;
        } else {
            WS_HIS_BVCODE = BPCUBVRV.DATA_INFO[1-1].BV_CODE1;
            WS_HIS_HEADNO = BPCUBVRV.DATA_INFO[1-1].HEAD_NO1;
            WS_HIS_BEGNO = BPCUBVRV.DATA_INFO[1-1].BEG_NO1;
            WS_HIS_ENDNO = BPCUBVRV.DATA_INFO[1-1].END_NO1;
            WS_HIS_NUMNO = BPCUBVRV.DATA_INFO[1-1].NUM1;
        }
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVDE_WS2);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO157;
