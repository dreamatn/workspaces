package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMLMA {
    DBParm CITLMA_RD;
    brParm CITLMA_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "LMA     INFO        ";
    String K_HIS_CPY = "CIRLMA";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI502";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_LMA = 1;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICCLST CICCLST = new CICCLST();
    CIRLMA CIRLMA = new CIRLMA();
    CICLLMA CICLLMA = new CICLLMA();
    CIRLMA CIRLMAO = new CIRLMA();
    CIRLMA CIRLMAN = new CIRLMA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMLMA CICMLMA;
    public void MP(SCCGWA SCCGWA, CICMLMA CICMLMA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMLMA = CICMLMA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMLMA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRLMA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_LMA_INPUT_DATA();
        if (pgmRtn) return;
        if (CICMLMA.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if ((CICMLMA.FUNC == 'C' 
                || CICMLMA.FUNC == 'D')) {
            B040_DELETE_PROC();
            if (pgmRtn) return;
        } else if ((CICMLMA.FUNC == 'U' 
                || CICMLMA.FUNC == 'M')) {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMLMA.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_LMA_INPUT_ERR;
        }
    }
    public void B010_CHECK_LMA_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLMA);
        IBS.init(SCCGWA, CIRLMAO);
        IBS.init(SCCGWA, CIRLMAN);
        CIRLMA.EX_APR_NO = CICMLMA.DATA.EX_APR_NO;
        CIRLMA.KEY.AC_NO = CICMLMA.DATA.NEW_AC_NO;
        T000_READ_CITLMA_CHK_ONLY();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        T000_WRITE_CITLMA();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLMA, CIRLMAN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLMA);
        IBS.init(SCCGWA, CIRLMAO);
        IBS.init(SCCGWA, CIRLMAN);
        CIRLMA.EX_APR_NO = CICMLMA.DATA.NEW_EX_APR_NO;
        T000_READ_CITLMA_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (CICMLMA.FUNC == 'U') {
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_LMA_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRLMA, CIRLMAO);
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        T000_REWRITE_CITLMA();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRLMA, CIRLMAN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLMA);
        IBS.init(SCCGWA, CIRLMAO);
        IBS.init(SCCGWA, CIRLMAN);
        CIRLMA.EX_APR_NO = CICMLMA.DATA.EX_APR_NO;
        CIRLMA.KEY.AC_NO = CICMLMA.DATA.AC_NO;
        T000_READ_CITLMA_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (CICMLMA.FUNC == 'C') {
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_LMA_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRLMA, CIRLMAO);
        T000_DELETE_CITLMA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B080_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLMA);
        CIRLMA.EX_APR_NO = CICMLMA.DATA.EX_APR_NO;
        T000_STARTBR_CITLMA();
        if (pgmRtn) return;
        B080_01_OUT_TITLE();
        if (pgmRtn) return;
        T000_READNEXT_CITLMA();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITLMA();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITLMA();
        if (pgmRtn) return;
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_LMA;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICLLMA);
        CICLLMA.AC_NO = CIRLMA.KEY.AC_NO;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICLLMA);
        SCCMPAG.DATA_LEN = 32;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 138;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRLMAO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRLMAN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_TBL() throws IOException,SQLException,Exception {
        if ((CICMLMA.DATA.NEW_EX_APR_NO.trim().length() == 0)) {
            CIRLMA.EX_APR_NO = CICMLMA.DATA.EX_APR_NO;
        } else {
            CIRLMA.EX_APR_NO = CICMLMA.DATA.NEW_EX_APR_NO;
        }
        if (CICMLMA.FUNC == 'A') {
            CIRLMA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRLMA.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRLMA.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CIRLMA.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRLMA.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRLMA.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMLMA);
        CICMLMA.DATA.EX_APR_NO = CIRLMA.EX_APR_NO;
        CICMLMA.DATA.AC_NO = CIRLMA.KEY.AC_NO;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (CICMLMA.FUNC == 'A' 
            || CICMLMA.FUNC == 'D' 
            || CICMLMA.FUNC == 'M') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = CICMLMA;
        SCCFMT.DATA_LEN = 132;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_WRITE_CITLMA() throws IOException,SQLException,Exception {
        CITLMA_RD = new DBParm();
        CITLMA_RD.TableName = "CITLMA";
        IBS.WRITE(SCCGWA, CIRLMA, CITLMA_RD);
    }
    public void T000_READ_CITLMA_UPD() throws IOException,SQLException,Exception {
        CITLMA_RD = new DBParm();
        CITLMA_RD.TableName = "CITLMA";
        CITLMA_RD.upd = true;
        IBS.READ(SCCGWA, CIRLMA, CITLMA_RD);
    }
    public void T000_REWRITE_CITLMA() throws IOException,SQLException,Exception {
        CITLMA_RD = new DBParm();
        CITLMA_RD.TableName = "CITLMA";
        IBS.REWRITE(SCCGWA, CIRLMA, CITLMA_RD);
    }
    public void T000_DELETE_CITLMA() throws IOException,SQLException,Exception {
        CITLMA_RD = new DBParm();
        CITLMA_RD.TableName = "CITLMA";
        IBS.DELETE(SCCGWA, CIRLMA, CITLMA_RD);
    }
    public void T000_STARTBR_CITLMA() throws IOException,SQLException,Exception {
        CITLMA_BR.rp = new DBParm();
        CITLMA_BR.rp.TableName = "CITLMA";
        CITLMA_BR.rp.col = "AC_NO,EX_APR_NO,EFF_DT,EXP_DT";
        CITLMA_BR.rp.where = "EX_APR_NO = :CIRLMA.EX_APR_NO";
        IBS.STARTBR(SCCGWA, CIRLMA, this, CITLMA_BR);
        CEP.TRC(SCCGWA, CIRLMA.EX_APR_NO);
    }
    public void T000_READNEXT_CITLMA() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRLMA, this, CITLMA_BR);
    }
    public void T000_ENDBR_CITLMA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITLMA_BR);
    }
    public void T000_READ_CITLMA_CHK_ONLY() throws IOException,SQLException,Exception {
        CITLMA_RD = new DBParm();
        CITLMA_RD.TableName = "CITLMA";
        IBS.READ(SCCGWA, CIRLMA, CITLMA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_LMA_INF_EXIST);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, CICMLMA.FUNC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICMLMA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMLMA=");
            CEP.TRC(SCCGWA, CICMLMA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
