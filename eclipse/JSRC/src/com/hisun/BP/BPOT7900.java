package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7900 {
    brParm BPTOOCAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_QUERY_OCAC_INFO = "BP-P-QUERY-OCAC-INFO";
    String K_OUTPUT_FMT = "SCZ01";
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT7900_WS_DATA WS_DATA = new BPOT7900_WS_DATA();
    char WS_OUT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPOOCAC BPCPOOCAC = new BPCPOOCAC();
    BPROOCAC BPROOCAC = new BPROOCAC();
    BPCO7900_OPT_7900 BPCO7900_OPT_7900 = new BPCO7900_OPT_7900();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB7900_AWA_7900 BPB7900_AWA_7900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT7900 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7900_AWA_7900>");
        BPB7900_AWA_7900 = (BPB7900_AWA_7900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7900_AWA_7900.Q_TYP);
        CEP.TRC(SCCGWA, BPB7900_AWA_7900.AC);
        CEP.TRC(SCCGWA, BPB7900_AWA_7900.ID_TYP);
        CEP.TRC(SCCGWA, BPB7900_AWA_7900.ID_NO);
        CEP.TRC(SCCGWA, BPB7900_AWA_7900.CI_CNM);
        if (BPB7900_AWA_7900.Q_TYP == ' ') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_QTYP_CANNOT_SPACE);
        }
        if (BPB7900_AWA_7900.Q_TYP != '1') {
            if (BPB7900_AWA_7900.Q_TYP != '2') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_QTYP_CANNOT_SPACE);
            }
        }
        if (BPB7900_AWA_7900.Q_TYP == '1') {
            if (BPB7900_AWA_7900.AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_CANNOT_SPACE);
            }
        }
        if (BPB7900_AWA_7900.Q_TYP == '2') {
            if (BPB7900_AWA_7900.ID_TYP.trim().length() == 0 
                || BPB7900_AWA_7900.ID_NO.trim().length() == 0 
                || BPB7900_AWA_7900.CI_CNM.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ID_CANNOT_SPACE);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOOCAC);
        BPCPOOCAC.DATA_INFO.ID_TYP = BPB7900_AWA_7900.ID_TYP;
        BPCPOOCAC.DATA_INFO.ID_NO = BPB7900_AWA_7900.ID_NO;
        BPCPOOCAC.DATA_INFO.CI_CNM = BPB7900_AWA_7900.CI_CNM;
        BPCPOOCAC.DATA_INFO.AC = BPB7900_AWA_7900.AC;
        if (BPB7900_AWA_7900.Q_TYP == '1') {
            BPCPOOCAC.INFO.INDEX_FLG = "1";
        } else if (BPB7900_AWA_7900.Q_TYP == '2') {
            BPCPOOCAC.INFO.INDEX_FLG = "2";
        }
        B021_BROWSE_PROC();
        if (pgmRtn) return;
    }
    public void B021_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPROOCAC);
        T000_STARTBR_BPTOOCAC();
        if (pgmRtn) return;
        T000_READNEXT_BPTOOCAC();
        if (pgmRtn) return;
        if (BPCPOOCAC.RETURN_INFO == 'F') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCPOOCAC.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPROOCAC);
            T000_READNEXT_BPTOOCAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTOOCAC();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTOOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, BPCPOOCAC.INFO.INDEX_FLG);
        CEP.TRC(SCCGWA, BPCPOOCAC.DATA_INFO.ID_TYP);
        CEP.TRC(SCCGWA, BPCPOOCAC.DATA_INFO.ID_NO);
        CEP.TRC(SCCGWA, BPCPOOCAC.DATA_INFO.CI_CNM);
        CEP.TRC(SCCGWA, BPCPOOCAC.DATA_INFO.AC);
        BPROOCAC.ID_TYP = BPCPOOCAC.DATA_INFO.ID_TYP;
        BPROOCAC.ID_NO = BPCPOOCAC.DATA_INFO.ID_NO;
        BPROOCAC.CI_CNM = BPCPOOCAC.DATA_INFO.CI_CNM;
        BPROOCAC.KEY.AC = BPCPOOCAC.DATA_INFO.AC;
        if (BPCPOOCAC.INFO.INDEX_FLG.equalsIgnoreCase("2")) {
            CEP.TRC(SCCGWA, BPROOCAC.ID_TYP);
            CEP.TRC(SCCGWA, BPROOCAC.ID_NO);
            CEP.TRC(SCCGWA, BPROOCAC.CI_CNM);
            BPTOOCAC_BR.rp = new DBParm();
            BPTOOCAC_BR.rp.TableName = "BPTOOCAC";
            BPTOOCAC_BR.rp.where = "ID_TYP = :BPROOCAC.ID_TYP "
                + "AND ID_NO = :BPROOCAC.ID_NO "
                + "AND CI_CNM = :BPROOCAC.CI_CNM";
            BPTOOCAC_BR.rp.order = "OPEN_DATE DESC";
            IBS.STARTBR(SCCGWA, BPROOCAC, this, BPTOOCAC_BR);
        }
        if (BPCPOOCAC.INFO.INDEX_FLG.equalsIgnoreCase("1")) {
            BPTOOCAC_BR.rp = new DBParm();
            BPTOOCAC_BR.rp.TableName = "BPTOOCAC";
            BPTOOCAC_BR.rp.where = "AC = :BPROOCAC.KEY.AC";
            BPTOOCAC_BR.rp.order = "OPEN_DATE DESC";
            IBS.STARTBR(SCCGWA, BPROOCAC, this, BPTOOCAC_BR);
        }
    }
    public void T000_READNEXT_BPTOOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        CEP.TRC(SCCGWA, BPCPOOCAC.INFO.INDEX_FLG);
        BPTOOCAC_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPROOCAC, this, BPTOOCAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPOOCAC.RETURN_INFO = 'F';
            CEP.TRC(SCCGWA, 11111111);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, 22222222);
            BPCPOOCAC.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OCAC_NOTFND, BPCPOOCAC.RC);
        } else {
            CEP.TRC(SCCGWA, 33333333);
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTOOCAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTOOCAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTOOCAC_BR);
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO7900_OPT_7900);
        BPCO7900_OPT_7900.AC = BPROOCAC.KEY.AC;
        BPCO7900_OPT_7900.WORK_TYP = BPROOCAC.WORK_TYP;
        BPCO7900_OPT_7900.TERM = BPROOCAC.TERM;
        BPCO7900_OPT_7900.BV_CD = BPROOCAC.BV_CD;
        BPCO7900_OPT_7900.BV_NO = BPROOCAC.BV_NO;
        BPCO7900_OPT_7900.OPEN_DT = BPROOCAC.OPEN_DATE;
        BPCO7900_OPT_7900.OPEN_BR = BPROOCAC.OPEN_BR;
        BPCO7900_OPT_7900.OPEN_TLR = BPROOCAC.OPEN_TLR;
        BPCO7900_OPT_7900.CLO_DT = BPROOCAC.CLOSE_DATE;
        BPCO7900_OPT_7900.CLO_BR = BPROOCAC.CLOSE_BR;
        BPCO7900_OPT_7900.CLO_TLR = BPROOCAC.CLOSE_TLR;
        BPCO7900_OPT_7900.CCY = BPROOCAC.CCY;
        BPCO7900_OPT_7900.CLO_AMT = BPROOCAC.CLOSE_AMT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCO7900_OPT_7900);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
