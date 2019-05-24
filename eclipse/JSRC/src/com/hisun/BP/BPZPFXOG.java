package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPFXOG {
    DBParm BPTFXORG_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    char WS_BRO_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPRFXORG BPRFXORG = new BPRFXORG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOFXOG BPCOFXOG = new BPCOFXOG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPFXOG BPCPFXOG;
    public void MP(SCCGWA SCCGWA, BPCPFXOG BPCPFXOG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPFXOG = BPCPFXOG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        C000_OUTPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPFXOG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPFXOG.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPFXOG.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCPFXOG.DATA_INFO.INQ_TYP);
        CEP.TRC(SCCGWA, BPCPFXOG.DATA_INFO.FX_NO);
        if (BPCPFXOG.INFO.FUNC == 'I') {
            if (BPCPFXOG.DATA_INFO.FX_NO.trim().length() == 0 
                || BPCPFXOG.DATA_INFO.BR == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
        } else {
            B020_MAIN_PROC();
            if (pgmRtn) return;
        }
        if (BPCPFXOG.INFO.FUNC == 'B') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCPFXOG.DATA_INFO.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.RC.RC_CODE != 0 
                && BPCPQORG.RC.RC_CODE == BPCMSG_ERROR_MSG.BP_ORG_NOTFND) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND);
            } else {
                CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
                if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
                    B020_MAIN_PROC();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, BPCOFXOG);
                    B060_01_DATA_TRANS_TO_FMT();
                    if (pgmRtn) return;
                    BPCOFXOG.BR = BPCPFXOG.DATA_INFO.BR;
                    B060_02_DATA_OUTPUT_FMT();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPFXOG.INFO.FUNC);
        if (BPCPFXOG.INFO.FUNC == 'I') {
            B020_INQUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCPFXOG.INFO.FUNC == 'B') {
            B020_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCPFXOG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B030_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B030_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFXOG);
        CEP.TRC(SCCGWA, BPRFXORG.KEY.BR);
        CEP.TRC(SCCGWA, BPRFXORG.KEY.FX_NO);
        CEP.TRC(SCCGWA, BPRFXORG.INQ_TYP);
        CEP.TRC(SCCGWA, BPRFXORG.STS);
        CEP.TRC(SCCGWA, BPRFXORG.FX_BR);
        BPCPFXOG.DATA_INFO.BR = BPRFXORG.KEY.BR;
        BPCPFXOG.DATA_INFO.FX_NO = BPRFXORG.KEY.FX_NO;
        BPCPFXOG.DATA_INFO.INQ_TYP = BPRFXORG.INQ_TYP;
        BPCPFXOG.DATA_INFO.STS = BPRFXORG.STS;
        BPCPFXOG.DATA_INFO.FX_BR = BPRFXORG.FX_BR;
    }
    public void B020_INQUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFXORG);
        T000_READ_BPTFXOG();
        if (pgmRtn) return;
        if (BPCPFXOG.RETURN_INFO == 'F') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B060_02_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND);
        }
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFXORG);
        BPRFXORG.KEY.BR = BPCPFXOG.DATA_INFO.BR;
        T000_READ_BPTFXOG_TYP1();
        if (pgmRtn) return;
        if (BPCPFXOG.RETURN_INFO == 'F') {
            B060_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B060_02_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            BPRFXORG.KEY.BR = BPCPQORG.BRANCH_BR;
            T000_READ_BPTFXOG_TYP2();
            if (pgmRtn) return;
            if (BPCPFXOG.RETURN_INFO == 'F') {
                B060_01_DATA_TRANS_TO_FMT();
                if (pgmRtn) return;
                B060_02_DATA_OUTPUT_FMT();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND);
            }
        }
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 34;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFXOG);
        CEP.TRC(SCCGWA, BPRFXORG.KEY.BR);
        CEP.TRC(SCCGWA, BPRFXORG.KEY.FX_NO);
        CEP.TRC(SCCGWA, BPRFXORG.INQ_TYP);
        CEP.TRC(SCCGWA, BPRFXORG.STS);
        CEP.TRC(SCCGWA, BPRFXORG.FX_BR);
        BPCOFXOG.BR = BPRFXORG.KEY.BR;
        BPCOFXOG.FX_NO = BPRFXORG.KEY.FX_NO;
        BPCOFXOG.INQ_TYP = BPRFXORG.INQ_TYP;
        BPCOFXOG.STS = BPRFXORG.STS;
        BPCOFXOG.FX_BR = BPRFXORG.FX_BR;
    }
    public void C000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFXORG.KEY.BR);
        CEP.TRC(SCCGWA, BPRFXORG.KEY.FX_NO);
        CEP.TRC(SCCGWA, BPRFXORG.INQ_TYP);
        CEP.TRC(SCCGWA, BPRFXORG.STS);
        CEP.TRC(SCCGWA, BPRFXORG.FX_BR);
        BPCPFXOG.DATA_INFO.BR = BPRFXORG.KEY.BR;
        BPCPFXOG.DATA_INFO.FX_NO = BPRFXORG.KEY.FX_NO;
        BPCPFXOG.DATA_INFO.INQ_TYP = BPRFXORG.INQ_TYP;
        BPCPFXOG.DATA_INFO.STS = BPRFXORG.STS;
        BPCPFXOG.DATA_INFO.FX_BR = BPRFXORG.FX_BR;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOFXOG);
        SCCMPAG.DATA_LEN = 34;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCPFXOG.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOFXOG;
        SCCFMT.DATA_LEN = 34;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTFXOG() throws IOException,SQLException,Exception {
        BPRFXORG.KEY.BR = BPCPFXOG.DATA_INFO.BR;
        BPRFXORG.KEY.FX_NO = BPCPFXOG.DATA_INFO.FX_NO;
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.where = "BR = :BPRFXORG.KEY.BR "
            + "AND FX_NO = :BPRFXORG.KEY.FX_NO";
        BPTFXORG_RD.fst = true;
        IBS.READ(SCCGWA, BPRFXORG, this, BPTFXORG_RD);
        CEP.TRC(SCCGWA, BPCPFXOG.RC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPFXOG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPFXOG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCPFXOG.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFXORG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFXOG_TYP1() throws IOException,SQLException,Exception {
        BPRFXORG.INQ_TYP = '1';
        BPRFXORG.STS = '1';
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.where = "INQ_TYP = :BPRFXORG.INQ_TYP "
            + "AND BR = :BPRFXORG.KEY.BR "
            + "AND STS = :BPRFXORG.STS";
        BPTFXORG_RD.fst = true;
        IBS.READ(SCCGWA, BPRFXORG, this, BPTFXORG_RD);
        CEP.TRC(SCCGWA, BPCPFXOG.RC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPFXOG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPFXOG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCPFXOG.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFXORG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFXOG_TYP2() throws IOException,SQLException,Exception {
        BPRFXORG.INQ_TYP = '2';
        BPRFXORG.STS = '1';
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.where = "INQ_TYP = :BPRFXORG.INQ_TYP "
            + "AND BR = :BPRFXORG.KEY.BR "
            + "AND STS = :BPRFXORG.STS";
        BPTFXORG_RD.fst = true;
        IBS.READ(SCCGWA, BPRFXORG, this, BPTFXORG_RD);
        CEP.TRC(SCCGWA, BPCPFXOG.RC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPFXOG.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPFXOG.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCPFXOG.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFXORG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPFXOG.RC);
            Z_RET();
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPFXOG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPOACAC = ");
            CEP.TRC(SCCGWA, BPCPFXOG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
