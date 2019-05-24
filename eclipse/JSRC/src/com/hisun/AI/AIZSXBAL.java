package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSXBAL {
    brParm AITFXBAL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    AIZSXBAL_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIZSXBAL_WS_BRW_OUTPUT();
    char SXBAL_RETURN_INFO = ' ';
    String WS_ERR_MSG = " ";
    int WS_SUPR_ORG = 0;
    int WS_CNTA = 0;
    char WS_BR_FLG = ' ';
    char WS_BRANCH_FLG = ' ';
    String TBL_AITFXBAL = "AITFXBAL";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRFXBAL AIRFXBAL = new AIRFXBAL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCMSG SCCMSG = new SCCMSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    int WS_BR_START = 0;
    int WS_BR_END = 0;
    SCCGWA SCCGWA;
    AICSXBAL AICSXBAL;
    public void MP(SCCGWA SCCGWA, AICSXBAL AICSXBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSXBAL = AICSXBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSXBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 20;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRFXBAL);
        AIRFXBAL.KEY.GL_BOOK_FLG = AICSXBAL.GL_BOOK;
        CEP.TRC(SCCGWA, AIRFXBAL.KEY.GL_BOOK_FLG);
        if (AICSXBAL.BR_ST == 0) {
            if ("000000".trim().length() == 0) WS_BR_START = 0;
            else WS_BR_START = Integer.parseInt("000000");
        } else {
            WS_BR_START = AICSXBAL.BR_ST;
        }
        if (AICSXBAL.BR_EN == 0) {
            if ("999999".trim().length() == 0) WS_BR_END = 0;
            else WS_BR_END = Integer.parseInt("999999");
        } else {
            WS_BR_END = AICSXBAL.BR_EN;
        }
        CEP.TRC(SCCGWA, "BB33B");
        CEP.TRC(SCCGWA, AICSXBAL.BR_ST);
        CEP.TRC(SCCGWA, AICSXBAL.BR_EN);
        T222_GET_THE_UNDER();
        if (pgmRtn) return;
        T000_STARTBR_AITFXBAL();
        if (pgmRtn) return;
        T000_READNEXT_AITFXBAL();
        if (pgmRtn) return;
        if (SXBAL_RETURN_INFO == 'N') {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (SXBAL_RETURN_INFO != 'N') {
            if (BPCPQORG.LVL != '9') {
                for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                    BPCPORLO.BR = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                    if (BPCPORLO.BR == AIRFXBAL.KEY.BR) {
                        B021_TRANS_DATA_BRW_OUTPUT();
                        if (pgmRtn) return;
                    }
                }
            } else {
                B021_TRANS_DATA_BRW_OUTPUT();
                if (pgmRtn) return;
            }
            T000_READNEXT_AITFXBAL();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITFXBAL();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B021_TRANS_DATA_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_GL_BOOK_FLG = AIRFXBAL.KEY.GL_BOOK_FLG;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_BR = AIRFXBAL.KEY.BR;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_RLTBAL = AIRFXBAL.RLTBAL;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_DEPALND = AIRFXBAL.DEPALND;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_DEPALNC = AIRFXBAL.DEPALNC;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_IBBUSIC = AIRFXBAL.IBBUSID;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_IBBUSID = AIRFXBAL.IBBUSIC;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_SFXBALC = AIRFXBAL.SFXBALC;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_SFXBALD = AIRFXBAL.SFXBALD;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER1D = AIRFXBAL.OTHER1D;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER1C = AIRFXBAL.OTHER1C;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER2D = AIRFXBAL.OTHER2D;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER2C = AIRFXBAL.OTHER2C;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER3D = AIRFXBAL.OTHER3D;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER3C = AIRFXBAL.OTHER3C;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER4D = AIRFXBAL.OTHER4D;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_OTHER4C = AIRFXBAL.OTHER4C;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_MARGIN = AIRFXBAL.MARGIN;
        CEP.TRC(SCCGWA, AIRFXBAL.KEY.BR);
        CEP.TRC(SCCGWA, AIRFXBAL.DEPALND);
        CEP.TRC(SCCGWA, AIRFXBAL.IBBUSID);
        CEP.TRC(SCCGWA, AIRFXBAL.SFXBALD);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 315;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_AITFXBAL() throws IOException,SQLException,Exception {
        AITFXBAL_BR.rp = new DBParm();
        AITFXBAL_BR.rp.TableName = "AITFXBAL";
        AITFXBAL_BR.rp.where = "GL_BOOK_FLG = :AIRFXBAL.KEY.GL_BOOK_FLG "
            + "AND BR >= :WS_BR_START "
            + "AND BR <= :WS_BR_END";
        IBS.STARTBR(SCCGWA, AIRFXBAL, this, AITFXBAL_BR);
    }
    public void T000_READNEXT_AITFXBAL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRFXBAL, this, AITFXBAL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SXBAL_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SXBAL_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITFXBAL;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITFXBAL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITFXBAL_BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
    }
    public void T222_GET_THE_UNDER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, "GETAABB");
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.LVL);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        if (BPCPQORG.LVL == '2') {
            WS_BRANCH_FLG = 'Y';
        } else {
            WS_BRANCH_FLG = 'N';
        }
        if (WS_BRANCH_FLG == 'N' 
            && BPCPQORG.LVL != '9') {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, BPCPORLO.BR);
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            CEP.TRC(SCCGWA, BPCPORLO);
            if (BPCPORLO.SUB_NUM == 0) {
                IBS.init(SCCGWA, BPCPORLO);
                BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                WS_SUPR_ORG = BPCPQORG.SUPR_BR;
                BPCPORLO.BR = WS_SUPR_ORG;
                CEP.TRC(SCCGWA, BPCPORLO.BR);
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                CEP.TRC(SCCGWA, BPCPORLO);
            }
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
