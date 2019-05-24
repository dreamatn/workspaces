package com.hisun.FX;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class FXZSTHIS {
    DBParm FXTTXHIS_RD;
    brParm FXTTXHIS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_FXZSTHIS = "FXZSTHIS";
    String TBL_FXTTXHIS = "FXTTXHIS";
    int WS_REC_LEN = 0;
    FXZSTHIS_WS_FXHIS_REC WS_FXHIS_REC = new FXZSTHIS_WS_FXHIS_REC();
    char FXZSTHIS_FILLER19 = 0X02;
    char WS_FXHIS_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FXRTXHIS FXRTXHIS = new FXRTXHIS();
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    String WS_CTA_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    FXCSTHIS FXCSTHIS;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, FXCSTHIS FXCSTHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCSTHIS = FXCSTHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZSTHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRTXHIS);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        FXCSTHIS.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        FXCSTHIS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCSTHIS.FUNC);
        CEP.TRC(SCCGWA, FXCSTHIS.STR_DT);
        CEP.TRC(SCCGWA, FXCSTHIS.END_DT);
        CEP.TRC(SCCGWA, FXCSTHIS.CTA_NO);
        if (FXCSTHIS.FUNC == 'B') {
            B005_BRW_FXTTHIS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + FXCSTHIS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B002_ADD_FXTTHIS_PROC() throws IOException,SQLException,Exception {
        FXTTXHIS_RD = new DBParm();
        FXTTXHIS_RD.TableName = "FXTTXHIS";
        IBS.WRITE(SCCGWA, FXRTXHIS, FXTTXHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_EXIST, FXCSTHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B005_BRW_FXTTHIS_PROC() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_STRBR_FXTTXHIS_PROC();
        if (pgmRtn) return;
        WS_FXHIS_END_FLG = 'N';
        while (WS_FXHIS_END_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                R000_TRANS_REL_MPAGE_OUTPUT();
                if (pgmRtn) return;
            }
        }
        B030_ENDBR_FXTTXHIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_FXHIS_REC);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 291;
        SCCMPAG.SCR_ROW_CNT = 10;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_REL_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FXHIS_REC);
        WS_FXHIS_REC.WS_FXHIS_TRN_DT = FXRTXHIS.KEY.TRN_DT;
        WS_FXHIS_REC.WS_FXHIS_JRN_NO = FXRTXHIS.KEY.JRN_NO;
        WS_FXHIS_REC.WS_FXHIS_CTA_NO = FXRTXHIS.CTA_NO;
        WS_FXHIS_REC.WS_FXHIS_PROD_CD = FXRTXHIS.PROD_CD;
        WS_FXHIS_REC.WS_FXHIS_SETL_TYPE = FXRTXHIS.SETL_TYPE;
        WS_FXHIS_REC.WS_FXHIS_TRN_CD = FXRTXHIS.TRN_CD;
        WS_FXHIS_REC.WS_FXHIS_TRN_NM = FXRTXHIS.TRN_NM;
        WS_FXHIS_REC.WS_FXHIS_PRE_STS = FXRTXHIS.PRE_STS;
        WS_FXHIS_REC.WS_FXHIS_POST_STS = FXRTXHIS.POST_STS;
        WS_FXHIS_REC.WS_FXHIS_HLD_NO = FXRTXHIS.HLD_NO;
        WS_FXHIS_REC.WS_FXHIS_ST_TRCHNL = FXRTXHIS.ST_TRCHNL;
        WS_FXHIS_REC.WS_FXHIS_CRT_TLR = FXRTXHIS.CRT_TLR;
        WS_FXHIS_REC.WS_FXHIS_CRT_BR = FXRTXHIS.CRT_BR;
        WS_FXHIS_REC.WS_FXHIS_TRN_TM = FXRTXHIS.TRN_TM;
        WS_FXHIS_REC.WS_FXHIS_RMK = FXRTXHIS.RMK;
        CEP.TRC(SCCGWA, WS_FXHIS_REC.WS_FXHIS_TRN_DT);
        CEP.TRC(SCCGWA, WS_FXHIS_REC.WS_FXHIS_JRN_NO);
        CEP.TRC(SCCGWA, WS_FXHIS_REC.WS_FXHIS_CTA_NO);
        WS_REC_LEN = 291;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_FXHIS_REC);
        SCCMPAG.DATA_LEN = (short) WS_REC_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_STRBR_FXTTXHIS_PROC() throws IOException,SQLException,Exception {
        WS_STR_DT = FXCSTHIS.STR_DT;
        WS_END_DT = FXCSTHIS.END_DT;
        WS_CTA_NO = FXCSTHIS.CTA_NO;
        FXTTXHIS_BR.rp = new DBParm();
        FXTTXHIS_BR.rp.TableName = "FXTTXHIS";
        FXTTXHIS_BR.rp.where = "( TRN_DT >= :WS_STR_DT "
            + "AND TRN_DT <= :WS_END_DT ) "
            + "AND CTA_NO = :WS_CTA_NO";
        IBS.STARTBR(SCCGWA, FXRTXHIS, this, FXTTXHIS_BR);
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FXRTXHIS, this, FXTTXHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FXHIS_END_FLG = 'Y';
        }
    }
    public void B030_ENDBR_FXTTXHIS_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FXTTXHIS_BR);
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
