package com.hisun.DD;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQBR {
    DBParm DDTHQBR_RD;
    brParm DDTHQBR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DDXXX";
    short K_MAX_ROW = 20;
    String WS_ERR_MSG = " ";
    DDZSQBR_WS_OUT_INF WS_OUT_INF = new DDZSQBR_WS_OUT_INF();
    char WS_INQ_FLG = ' ';
    char WS_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRHQBR DDRHQBR = new DDRHQBR();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSQBR DDCSQBR;
    public void MP(SCCGWA SCCGWA, DDCSQBR DDCSQBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQBR = DDCSQBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQBR return!");
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
        if (DDCSQBR.FUNC == '1') {
            B020_RAEDUP_DDTHQBR_PROC();
            if (pgmRtn) return;
        } else if (DDCSQBR.FUNC == '2') {
            R000_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            B030_QUERY_INFO();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQBR.FUNC);
        CEP.TRC(SCCGWA, DDCSQBR.SCD_BR);
        CEP.TRC(SCCGWA, DDCSQBR.SCD_BR_NM);
        CEP.TRC(SCCGWA, DDCSQBR.FST_BR);
        CEP.TRC(SCCGWA, DDCSQBR.FST_BR_NM);
        CEP.TRC(SCCGWA, DDCSQBR.OPEN_FLG);
        if (DDCSQBR.FUNC == '2') {
            if (DDCSQBR.SCD_BR == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSQBR.SCD_BR == 706660602) {
                WS_INQ_FLG = 'A';
            } else {
                WS_INQ_FLG = 'O';
            }
        }
    }
    public void B020_RAEDUP_DDTHQBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHQBR);
        DDRHQBR.KEY.SCD_BR = DDCSQBR.SCD_BR;
        T000_READUP_DDTHQBR();
        if (pgmRtn) return;
        DDRHQBR.SCD_BR_NM = DDCSQBR.SCD_BR_NM;
        DDRHQBR.FST_BR = DDCSQBR.FST_BR;
        DDRHQBR.FST_BR_NM = DDCSQBR.FST_BR_NM;
        DDRHQBR.OPEN_FLG = DDCSQBR.OPEN_FLG;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            T000_WRITE_DDTHQBR();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_DDTHQBR();
            if (pgmRtn) return;
        }
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 135;
        SCCMPAG.SCR_ROW_CNT = K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_QUERY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHQBR);
        if (WS_INQ_FLG == 'O') {
            T000_READ_DDTHQBR();
            if (pgmRtn) return;
            if (WS_REC_FLG == 'Y') {
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        if (WS_INQ_FLG == 'A') {
            T000_STARTBR_DDTHQBR();
            if (pgmRtn) return;
            T000_READNXT_DDTHQBR();
            if (pgmRtn) return;
            while (WS_REC_FLG != 'N') {
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
                T000_READNXT_DDTHQBR();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTHQBR();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_INF);
        WS_OUT_INF.WS_SCD_BR = DDRHQBR.KEY.SCD_BR;
        WS_OUT_INF.WS_SCD_BR_NM = DDRHQBR.SCD_BR_NM;
        WS_OUT_INF.WS_FST_BR = DDRHQBR.FST_BR;
        WS_OUT_INF.WS_FST_BR_NM = DDRHQBR.FST_BR_NM;
        WS_OUT_INF.WS_OPEN_FLG = DDRHQBR.OPEN_FLG;
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_SCD_BR);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_SCD_BR_NM);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_FST_BR);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_FST_BR_NM);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_OPEN_FLG);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_INF);
        SCCMPAG.DATA_LEN = 135;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_WRITE_DDTHQBR() throws IOException,SQLException,Exception {
        DDRHQBR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHQBR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTHQBR_RD = new DBParm();
        DDTHQBR_RD.TableName = "DDTHQBR";
        IBS.WRITE(SCCGWA, DDRHQBR, DDTHQBR_RD);
    }
    public void T000_REWRITE_DDTHQBR() throws IOException,SQLException,Exception {
        DDRHQBR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHQBR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTHQBR_RD = new DBParm();
        DDTHQBR_RD.TableName = "DDTHQBR";
        IBS.REWRITE(SCCGWA, DDRHQBR, DDTHQBR_RD);
    }
    public void T000_READ_DDTHQBR() throws IOException,SQLException,Exception {
        DDRHQBR.KEY.SCD_BR = DDCSQBR.SCD_BR;
        DDTHQBR_RD = new DBParm();
        DDTHQBR_RD.TableName = "DDTHQBR";
        IBS.READ(SCCGWA, DDRHQBR, DDTHQBR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
        }
    }
    public void T000_READUP_DDTHQBR() throws IOException,SQLException,Exception {
        DDTHQBR_RD = new DBParm();
        DDTHQBR_RD.TableName = "DDTHQBR";
        DDTHQBR_RD.upd = true;
        IBS.READ(SCCGWA, DDRHQBR, DDTHQBR_RD);
    }
    public void T000_STARTBR_DDTHQBR() throws IOException,SQLException,Exception {
        DDTHQBR_BR.rp = new DBParm();
        DDTHQBR_BR.rp.TableName = "DDTHQBR";
        IBS.STARTBR(SCCGWA, DDRHQBR, DDTHQBR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
        }
    }
    public void T000_READNXT_DDTHQBR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRHQBR, this, DDTHQBR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_DDTHQBR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHQBR_BR);
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
