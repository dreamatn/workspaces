package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZQCRDN {
    DBParm DCTCDDAT_RD;
    brParm DCTCDORD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 18;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZQCRDN_WS_OUTPUT_DATA WS_OUTPUT_DATA = new DCZQCRDN_WS_OUTPUT_DATA();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCQCRDN DCCQCRDN;
    public void MP(SCCGWA SCCGWA, DCCQCRDN DCCQCRDN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCQCRDN = DCCQCRDN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZQCRDN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCQCRDN);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_CARD_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCQCRDN.VAL.FUNC == 'C') {
            if (DCCQCRDN.VAL.PROD_OLD_CARD_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = DCCQCRDN.VAL.PROD_OLD_CARD_NO;
                T000_READ_DCTCDDAT();
                if (pgmRtn) return;
                DCCQCRDN.VAL.PROD_CD = DCRCDDAT.PROD_CD;
            }
        }
        if (DCCQCRDN.VAL.FUNC == 'O' 
            && DCCQCRDN.VAL.PROD_CD.trim().length() == 0) {
            DCCQCRDN.VAL.PROD_CD = "%%%%%%%%%%";
        }
    }
    public void B020_QUERY_CARD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.CARD_PROD = DCCQCRDN.VAL.PROD_CD;
        DCRCDORD.CRT_STS = '2';
        DCRCDORD.EMBS_TYP = '1';
        DCRCDORD.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DCRCDORD.CARD_PROD);
        CEP.TRC(SCCGWA, DCRCDORD.CRT_STS);
        CEP.TRC(SCCGWA, DCRCDORD.EMBS_TYP);
        CEP.TRC(SCCGWA, DCRCDORD.APP_BR);
        T000_STARTBR_DCTCDORD();
        if (pgmRtn) return;
        T000_READNEXT_DCTCDORD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B021_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
            B022_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_DCTCDORD();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCDORD();
        if (pgmRtn) return;
    }
    public void B021_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_CARD_NO = DCRCDORD.KEY.CARD_NO;
        WS_OUTPUT_DATA.WS_CARD_PROD = DCRCDORD.CARD_PROD;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 29;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_BR.rp = new DBParm();
        DCTCDORD_BR.rp.TableName = "DCTCDORD";
        DCTCDORD_BR.rp.where = "CARD_PROD LIKE :DCRCDORD.CARD_PROD "
            + "AND CRT_STS = :DCRCDORD.CRT_STS "
            + "AND EMBS_TYP = :DCRCDORD.EMBS_TYP "
            + "AND APP_BR = :DCRCDORD.APP_BR";
        DCTCDORD_BR.rp.order = "CARD_NO";
        IBS.STARTBR(SCCGWA, DCRCDORD, this, DCTCDORD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DCTCDORD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDORD, this, DCTCDORD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_ENDBR_DCTCDORD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDORD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
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
