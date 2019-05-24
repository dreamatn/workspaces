package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSAHQB {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DDTAHQB_RD;
    brParm DDTAHQB_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD870";
    short K_MAX_ROW = 20;
    String WS_ERR_MSG = " ";
    DDZSAHQB_WS_TEMP_BATNO WS_TEMP_BATNO = new DDZSAHQB_WS_TEMP_BATNO();
    String WS_BATCHNO = " ";
    int WS_AHQB_STRDT = 0;
    int WS_AHQB_EXPDT = 0;
    int WS_AHQB_BR = 0;
    DDZSAHQB_WS_OUT_INF WS_OUT_INF = new DDZSAHQB_WS_OUT_INF();
    char WS_INQB_FLG = ' ';
    char WS_INQD_FLG = ' ';
    char WS_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRAHQB DDRAHQB = new DDRAHQB();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSAHQB DDCSAHQB;
    public void MP(SCCGWA SCCGWA, DDCSAHQB DDCSAHQB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSAHQB = DDCSAHQB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSAHQB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCSAHQB.FUNC == '1') {
            B020_ADD_DDTAHQB_PROC();
            if (pgmRtn) return;
        } else if (DDCSAHQB.FUNC == '2') {
            B030_UPD_DDTAHQB_PROC();
            if (pgmRtn) return;
        } else if (DDCSAHQB.FUNC == '3') {
            R000_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B040_QUERY_INFO();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSAHQB.FUNC == '1' 
            || DDCSAHQB.FUNC == '2') {
            B050_OUTPUT_FMT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSAHQB.FUNC);
        CEP.TRC(SCCGWA, DDCSAHQB.AOIC_NM);
        CEP.TRC(SCCGWA, DDCSAHQB.AOIC_TEL);
        CEP.TRC(SCCGWA, DDCSAHQB.AP_CONM);
        CEP.TRC(SCCGWA, DDCSAHQB.CCY);
        CEP.TRC(SCCGWA, DDCSAHQB.CCY_TYP);
        CEP.TRC(SCCGWA, DDCSAHQB.SIGN_FLG);
        CEP.TRC(SCCGWA, DDCSAHQB.SIGN_NUM);
        CEP.TRC(SCCGWA, DDCSAHQB.AP_RSLT);
        CEP.TRC(SCCGWA, DDCSAHQB.APR_FLG);
        CEP.TRC(SCCGWA, DDCSAHQB.BAT_NO);
        CEP.TRC(SCCGWA, DDCSAHQB.STR_DT);
        CEP.TRC(SCCGWA, DDCSAHQB.END_DT);
        if (DDCSAHQB.FUNC == '2') {
            if (DDCSAHQB.BAT_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BATNO_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSAHQB.APR_FLG != 'Y' 
                && DDCSAHQB.APR_FLG != 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSAHQB.FUNC == '3') {
            if (DDCSAHQB.BAT_NO.trim().length() > 0) {
                WS_INQB_FLG = 'B';
            }
            if (DDCSAHQB.STR_DT != 0 
                && DDCSAHQB.END_DT != 0) {
                WS_INQD_FLG = 'D';
            }
            if (DDCSAHQB.STR_DT == 0 
                && DDCSAHQB.END_DT != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSAHQB.STR_DT != 0 
                && DDCSAHQB.END_DT == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSAHQB.STR_DT != 0 
                && DDCSAHQB.END_DT != 0 
                && DDCSAHQB.STR_DT > DDCSAHQB.END_DT) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DATE_LT_FROM_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_INQB_FLG == 'B' 
                && WS_INQD_FLG == 'D') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_ADD_DDTAHQB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "DC";
        BPCSGSEQ.CODE = "BATNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.NUM = 1;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(12 - 1, 12 + 4 - 1).trim().length() == 0) WS_TEMP_BATNO.WS_TEMP_SEQ = 0;
        else WS_TEMP_BATNO.WS_TEMP_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(12 - 1, 12 + 4 - 1));
        WS_TEMP_BATNO.WS_TEMP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_BATCHNO = IBS.CLS2CPY(SCCGWA, WS_TEMP_BATNO);
        IBS.init(SCCGWA, DDRAHQB);
        DDRAHQB.KEY.BATCH_NO = WS_BATCHNO;
        DDRAHQB.AC_OIC_NM = DDCSAHQB.AOIC_NM;
        DDRAHQB.AOIC_TEL = DDCSAHQB.AOIC_TEL;
        DDRAHQB.AP_CO_NM = DDCSAHQB.AP_CONM;
        DDRAHQB.CCY = DDCSAHQB.CCY;
        DDRAHQB.CCY_TYP = DDCSAHQB.CCY_TYP;
        DDRAHQB.SIGN_FLG = DDCSAHQB.SIGN_FLG;
        DDRAHQB.SIGN_NUM = DDCSAHQB.SIGN_NUM;
        DDRAHQB.STS = '0';
        DDRAHQB.AP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRAHQB.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRAHQB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRAHQB.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRAHQB.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRAHQB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTAHQB();
        if (pgmRtn) return;
    }
    public void B030_UPD_DDTAHQB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRAHQB);
        DDRAHQB.KEY.BATCH_NO = DDCSAHQB.BAT_NO;
        T000_READUPD_DDTAHQB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRAHQB.STS);
        if (DDRAHQB.STS != '0') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_APR_ALRDY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSAHQB.APR_FLG == 'Y') {
            DDRAHQB.STS = '1';
        } else {
            DDRAHQB.STS = '2';
        }
        DDRAHQB.AP_RSLT = DDCSAHQB.AP_RSLT;
        DDRAHQB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRAHQB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTAHQB();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 834;
        SCCMPAG.SCR_ROW_CNT = K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_QUERY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRAHQB);
        DDRAHQB.KEY.BATCH_NO = DDCSAHQB.BAT_NO;
        WS_AHQB_STRDT = DDCSAHQB.STR_DT;
        WS_AHQB_EXPDT = DDCSAHQB.END_DT;
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706660602) {
            WS_AHQB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (WS_INQB_FLG == 'B') {
            T000_READ_DDTAHQB();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == 706660602 
                || WS_AHQB_BR == DDRAHQB.TR_BR)) {
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        if (WS_INQD_FLG == 'D') {
            T000_STARTBR_DDTAHQB();
            if (pgmRtn) return;
            T000_READNXT_DDTAHQB();
            if (pgmRtn) return;
            while (WS_REC_FLG != 'N') {
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
                T000_READNXT_DDTAHQB();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTAHQB();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_INF);
        WS_OUT_INF.WS_RBAT_NO = DDRAHQB.KEY.BATCH_NO;
        WS_OUT_INF.WS_AOIC_NM = DDRAHQB.AC_OIC_NM;
        WS_OUT_INF.WS_AOIC_TEL = DDRAHQB.AOIC_TEL;
        WS_OUT_INF.WS_AP_CONM = DDRAHQB.AP_CO_NM;
        WS_OUT_INF.WS_SIGN_FLG = DDRAHQB.SIGN_FLG;
        WS_OUT_INF.WS_AP_DATE = DDRAHQB.AP_DATE;
        WS_OUT_INF.WS_STS = DDRAHQB.STS;
        WS_OUT_INF.WS_SIGN_NUM = DDRAHQB.SIGN_NUM;
        WS_OUT_INF.WS_TR_BR = DDRAHQB.TR_BR;
        WS_OUT_INF.WS_CCY = DDRAHQB.CCY;
        WS_OUT_INF.WS_CCY_TYP = DDRAHQB.CCY_TYP;
        WS_OUT_INF.WS_AP_RSLT = DDRAHQB.AP_RSLT;
        WS_OUT_INF.WS_CRT_TLR = DDRAHQB.CRT_TLR;
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_RBAT_NO);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_AOIC_NM);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_AOIC_TEL);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_AP_CONM);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_SIGN_FLG);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_AP_DATE);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_STS);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_SIGN_NUM);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_TR_BR);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_CCY_TYP);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_AP_RSLT);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_CRT_TLR);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_INF);
        SCCMPAG.DATA_LEN = 834;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_FMT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_INF);
        WS_OUT_INF.WS_FUNC = DDCSAHQB.FUNC;
        WS_OUT_INF.WS_RBAT_NO = DDRAHQB.KEY.BATCH_NO;
        WS_OUT_INF.WS_AOIC_NM = DDRAHQB.AC_OIC_NM;
        WS_OUT_INF.WS_AOIC_TEL = DDRAHQB.AOIC_TEL;
        WS_OUT_INF.WS_AP_CONM = DDRAHQB.AP_CO_NM;
        WS_OUT_INF.WS_SIGN_FLG = DDRAHQB.SIGN_FLG;
        WS_OUT_INF.WS_AP_DATE = DDRAHQB.AP_DATE;
        WS_OUT_INF.WS_STS = DDRAHQB.STS;
        WS_OUT_INF.WS_SIGN_NUM = DDRAHQB.SIGN_NUM;
        WS_OUT_INF.WS_TR_BR = DDRAHQB.TR_BR;
        WS_OUT_INF.WS_CCY = DDRAHQB.CCY;
        WS_OUT_INF.WS_CCY_TYP = DDRAHQB.CCY_TYP;
        WS_OUT_INF.WS_AP_RSLT = DDRAHQB.AP_RSLT;
        WS_OUT_INF.WS_CRT_TLR = DDRAHQB.CRT_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_INF;
        SCCFMT.DATA_LEN = 834;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTAHQB() throws IOException,SQLException,Exception {
        DDTAHQB_RD = new DBParm();
        DDTAHQB_RD.TableName = "DDTAHQB";
        DDTAHQB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRAHQB, DDTAHQB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_IS_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTAHQB() throws IOException,SQLException,Exception {
        DDTAHQB_RD = new DBParm();
        DDTAHQB_RD.TableName = "DDTAHQB";
        IBS.REWRITE(SCCGWA, DDRAHQB, DDTAHQB_RD);
    }
    public void T000_READ_DDTAHQB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRAHQB.KEY.BATCH_NO);
        DDTAHQB_RD = new DBParm();
        DDTAHQB_RD.TableName = "DDTAHQB";
        IBS.READ(SCCGWA, DDRAHQB, DDTAHQB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            DDRAHQB.KEY.BATCH_NO = " ";
        }
    }
    public void T000_READUPD_DDTAHQB() throws IOException,SQLException,Exception {
        DDTAHQB_RD = new DBParm();
        DDTAHQB_RD.TableName = "DDTAHQB";
        DDTAHQB_RD.upd = true;
        IBS.READ(SCCGWA, DDRAHQB, DDTAHQB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTAHQB() throws IOException,SQLException,Exception {
        DDTAHQB_BR.rp = new DBParm();
        DDTAHQB_BR.rp.TableName = "DDTAHQB";
        DDTAHQB_BR.rp.where = "AP_DATE >= :WS_AHQB_STRDT "
            + "AND AP_DATE <= :WS_AHQB_EXPDT "
            + "AND ( TR_BR = :WS_AHQB_BR "
            + "OR :WS_AHQB_BR = 0 )";
        IBS.STARTBR(SCCGWA, DDRAHQB, this, DDTAHQB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
        }
    }
    public void T000_READNXT_DDTAHQB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRAHQB, this, DDTAHQB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_DDTAHQB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTAHQB_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
