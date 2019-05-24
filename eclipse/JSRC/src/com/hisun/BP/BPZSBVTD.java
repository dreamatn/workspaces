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

public class BPZSBVTD {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP156";
    String CPN_U_TLR_BV_RVK = "BP-U-TLR-BV-RVK     ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    String BPZSBVTD_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPZSBVTD_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPZSBVTD_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPZSBVTD_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPZSBVTD_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCO156 BPCO156 = new BPCO156();
    BPCUBVRV BPCUBVRV = new BPCUBVRV();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVTD BPCSBVTD;
    public void MP(SCCGWA SCCGWA, BPCSBVTD BPCSBVTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVTD = BPCSBVTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVTD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        if (pgmRtn) return;
        B020_BV_TO_DESTROY();
        if (pgmRtn) return;
        B050_REC_NHIS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B060_DATA_OUTPUT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVTD.BV_CODE;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
    }
    public void B020_BV_TO_DESTROY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVRV);
        BPCUBVRV.FUNC = '0';
        BPCUBVRV.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPCUBVRV.VB_FLG = '1';
        BPCUBVRV.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVRV.BV_CODE = BPCSBVTD.BV_CODE;
        BPCUBVRV.HEAD_NO = BPCSBVTD.HEAD_NO;
        BPCUBVRV.BEG_NO = BPCSBVTD.BEG_NO;
        BPCUBVRV.END_NO = BPCSBVTD.END_NO;
        BPCUBVRV.NUM = BPCSBVTD.NUM;
        S000_CALL_BPZUBVRV();
        if (pgmRtn) return;
        B021_BUSE_PROCESS();
        if (pgmRtn) return;
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        CEP.TRC(SCCGWA, "B021-BUSE-PROCESS11");
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVTD.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.TYPE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.USE_MODE);
        if (BPCFBVQU.TX_DATA.TYPE != '1' 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            CEP.TRC(SCCGWA, "112233");
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B021_ADD_BUSE_PROCESS();
                if (pgmRtn) return;
            } else {
                B022_ADD_BUSE_PROCESS_CANCEL();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVTD.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVTD.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVTD.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVTD.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '1';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B022_ADD_BUSE_PROCESS_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVTD.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVTD.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVTD.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVTD.END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVTD.MOV_DT;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPRBUSE.KEY.TX_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, BPCRBUSE.RETURN_INFO);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.REC_STS = '1';
        BPCRBUSE.INFO.FUNC = 'U';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P910";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_BVCODE = BPCSBVTD.BV_CODE;
        WS_HIS_HEADNO = BPCSBVTD.HEAD_NO;
        WS_HIS_BEGNO = BPCSBVTD.BEG_NO;
        WS_HIS_ENDNO = BPCSBVTD.END_NO;
        WS_HIS_NUMNO = BPCSBVTD.NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVTD_WS2);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO156;
