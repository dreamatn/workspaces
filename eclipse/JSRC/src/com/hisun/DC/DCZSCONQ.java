package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCONQ {
    DBParm DCTCITCD_RD;
    DBParm DCTCDDAT_RD;
    brParm DCTCITCD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    DCZSCONQ_WS_SQL_VARIABLES WS_SQL_VARIABLES = new DCZSCONQ_WS_SQL_VARIABLES();
    DCZSCONQ_WS_OUTPUT WS_OUTPUT = new DCZSCONQ_WS_OUTPUT();
    DCZSCONQ_WS_OUTPUT1 WS_OUTPUT1 = new DCZSCONQ_WS_OUTPUT1();
    DCZSCONQ_WS_VARIABLES WS_VARIABLES = new DCZSCONQ_WS_VARIABLES();
    DCZSCONQ_WS_COND_FLG WS_COND_FLG = new DCZSCONQ_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSCONQ DCCSCONQ;
    public void MP(SCCGWA SCCGWA, DCCSCONQ DCCSCONQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCONQ = DCCSCONQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCONQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, WS_OUTPUT1);
        WS_COND_FLG.TBL_FLAG = 'Y';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCONQ.FUNC_CD);
        if (DCCSCONQ.FUNC_CD == 'B') {
            B100_PLAN_BROWSE();
            if (pgmRtn) return;
        } else if (DCCSCONQ.FUNC_CD == 'Q') {
            B200_PLAN_INQURY();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B100_PLAN_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCONQ.BRO_COND);
        CEP.TRC(SCCGWA, DCCSCONQ.CARD_NO);
        CEP.TRC(SCCGWA, DCCSCONQ.CONF_STS);
        CEP.TRC(SCCGWA, DCCSCONQ.ID_TYP);
        CEP.TRC(SCCGWA, DCCSCONQ.ID_NO);
        CEP.TRC(SCCGWA, DCCSCONQ.START_DT);
        CEP.TRC(SCCGWA, DCCSCONQ.END_DT);
        if (DCCSCONQ.BRO_COND == '1') {
            B110_CNO_BROWSE();
            if (pgmRtn) return;
        } else if (DCCSCONQ.BRO_COND == '2') {
            B120_ID_BROWSE();
            if (pgmRtn) return;
        } else if (DCCSCONQ.BRO_COND == '3') {
            B130_DT_BROWSE();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B110_CNO_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "55555");
        IBS.init(SCCGWA, DCRCITCD);
        DCRCITCD.KEY.CARD_NO = DCCSCONQ.CARD_NO;
        T000_READ_DCTCITCD();
        if (pgmRtn) return;
        C000_OUTPUT_INI();
        if (pgmRtn) return;
        C100_OUTPUT_LIST();
        if (pgmRtn) return;
    }
    public void B120_ID_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCONQ.CI_NO);
        IBS.init(SCCGWA, DCRCDDAT);
        IBS.init(SCCGWA, DCRCITCD);
        WS_SQL_VARIABLES.CARD_HLDR_CINO = DCCSCONQ.CI_NO;
        CEP.TRC(SCCGWA, WS_SQL_VARIABLES.CARD_HLDR_CINO);
        WS_SQL_VARIABLES.PROD_CD = "1203010101";
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        DCRCITCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        T000_READ_DCTCITCD();
        if (pgmRtn) return;
        C000_OUTPUT_INI();
        if (pgmRtn) return;
        C100_OUTPUT_LIST();
        if (pgmRtn) return;
    }
    public void B130_DT_BROWSE() throws IOException,SQLException,Exception {
        WS_SQL_VARIABLES.SQL_CONF_STS = DCCSCONQ.CONF_STS;
        WS_SQL_VARIABLES.SQL_START_DT = DCCSCONQ.START_DT;
        WS_SQL_VARIABLES.SQL_END_DT = DCCSCONQ.END_DT;
        IBS.init(SCCGWA, DCRCITCD);
        T000_STARTBR_DCTCITCD();
        if (pgmRtn) return;
        T100_READNEXT_DCTCITCD();
        if (pgmRtn) return;
        C000_OUTPUT_INI();
        if (pgmRtn) return;
        while (WS_COND_FLG.TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            C100_OUTPUT_LIST();
            if (pgmRtn) return;
            T100_READNEXT_DCTCITCD();
            if (pgmRtn) return;
        }
        T200_ENDBR_DCTCITCD();
        if (pgmRtn) return;
    }
    public void C000_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 371;
        SCCMPAG.SCR_ROW_CNT = MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, CICCUST);
        WS_OUTPUT.B_CARD_NO = DCRCITCD.KEY.CARD_NO;
        WS_OUTPUT.B_CI_NAME = DCRCITCD.CI_NM;
        WS_OUTPUT.B_ID_TYP = DCRCITCD.ID_TYP;
        WS_OUTPUT.B_ID_NO = DCRCITCD.ID_NO;
        WS_OUTPUT.B_CONF_STS = DCRCITCD.CONF_STS;
        WS_OUTPUT.B_CONF_DT = DCRCITCD.CONF_DT;
        WS_OUTPUT.B_CONF_TLR = DCRCITCD.CONF_TLR;
        WS_OUTPUT.B_CONF_BR = DCRCITCD.CONF_BR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 371;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B200_PLAN_INQURY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCONQ.CARD_NO);
        if (DCCSCONQ.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_WRITE_ERR_MSG();
            if (pgmRtn) return;
        }
        if (DCCSCONQ.CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = DCCSCONQ.CARD_NO;
            T000_READ_DCTCITCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRCITCD.CONF_STS);
            WS_OUTPUT1.O_CONF_STS = DCRCITCD.CONF_STS;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = OUTPUT_FMT_A;
            SCCFMT.DATA_PTR = WS_OUTPUT1;
            SCCFMT.DATA_LEN = 1;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "123456789");
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCITCD.KEY.CARD_NO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_CDDAT_NOTFND;
            S000_WRITE_ERR_MSG();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "CARD_HLDR_CINO = :WS_SQL_VARIABLES.CARD_HLDR_CINO "
            + "AND PROD_CD = :WS_SQL_VARIABLES.PROD_CD "
            + "AND CARD_STS NOT IN ( 'C' )";
        DCTCDDAT_RD.fst = true;
        DCTCDDAT_RD.order = "ISSU_DT DESC";
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_BR.rp = new DBParm();
        DCTCITCD_BR.rp.TableName = "DCTCITCD";
        DCTCITCD_BR.rp.where = "SYNC_DT >= :WS_SQL_VARIABLES.SQL_START_DT "
            + "AND SYNC_DT <= :WS_SQL_VARIABLES.SQL_END_DT "
            + "AND ( CONF_STS = :WS_SQL_VARIABLES.SQL_CONF_STS )";
        IBS.STARTBR(SCCGWA, DCRCITCD, this, DCTCITCD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCITCD.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_CDDAT_NOTFND;
            S000_WRITE_ERR_MSG();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T100_READNEXT_DCTCITCD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCITCD, this, DCTCITCD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T200_ENDBR_DCTCITCD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCITCD_BR);
    }
    public void S000_WRITE_ERR_MSG() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
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
